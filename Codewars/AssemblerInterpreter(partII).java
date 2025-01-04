import java.util.*;
import java.util.function.*;
import java.util.regex.*;

record Arguments(String targetName, Object source) {
}

@FunctionalInterface
interface Instruction {
    void apply(Arguments args, Map<String, Integer> registers, Stack<Integer> history);

    static void binaryConsume(Arguments args, Map<String, Integer> registers, IntBinaryOperator op) {
        String register = args.getTargetName();
        int val = registers.getOrDefault(register, 0);
        OptionalInt opt = AssemblerInterpreter.getIfNum(args.getSource());
        opt.ifPresentOrElse(x -> registers.put(register, op.applyAsInt(val, x)), () ->
                registers.put(register, op.applyAsInt(val, registers.get(args.getSource().toString()))));
    }

    static int compare(Arguments args, Map<String, Integer> registers) {
        int val1 = registers.getOrDefault(args.getTargetName(), 0);
        String s2 = args.getSource().toString();
        OptionalInt opt = AssemblerInterpreter.getIfNum(s2);
        return opt.isPresent() ? Integer.compare(val1, opt.getAsInt()) : Integer.compare(val1, registers.getOrDefault(s2, 0));
    }
}

class AssemblerInterpreter {

    private static final Map<String, Instruction> instructionMap = new HashMap<>();
    private static final Pattern LABEL_PATTERN = Pattern.compile("^.*:$");
    private static Map<String, Integer> labelMap;
    private static Arguments last;
    private static boolean cmp;
    private static int pc;
    private static StringBuilder resultBuilder;
    private static boolean done;

    static OptionalInt getIfNum(Object n) {
        if (n == null) return OptionalInt.empty();
        try {
            return OptionalInt.of(Integer.parseInt(n.toString()));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    static {
        initializeInstructions();
    }

    private static void initializeInstructions() {
        instructionMap.put("mov", (args, registers, history) -> {
            String register = args.getTargetName();
            OptionalInt opt = getIfNum(args.getSource());
            opt.ifPresentOrElse(x -> registers.put(register, x), () ->
                    registers.put(register, registers.get(args.getSource().toString())));
        });

        instructionMap.put("inc", (args, registers, history) -> {
            String register = args.getTargetName();
            int val = registers.getOrDefault(register, 0);
            registers.put(register, val + 1);
        });

        instructionMap.put("dec", (args, registers, history) -> {
            String register = args.getTargetName();
            int val = registers.getOrDefault(register, 0);
            registers.put(register, val - 1);
        });

        instructionMap.put("add", (args, registers, history) ->
                Instruction.binaryConsume(args, registers, Integer::sum));

        instructionMap.put("sub", (args, registers, history) ->
                Instruction.binaryConsume(args, registers, (x, y) -> x - y));

        instructionMap.put("mul", (args, registers, history) ->
                Instruction.binaryConsume(args, registers, (x, y) -> x * y));

        instructionMap.put("div", (args, registers, history) ->
                Instruction.binaryConsume(args, registers, (x, y) -> x / y));

        instructionMap.put("jmp", (args, registers, history) -> {
            history.push(pc);
            pc = labelMap.get(args.getTargetName());
        });

        instructionMap.put("jne", (args, registers, history) -> {
            int result = Instruction.compare(last, registers);
            if (result != 0 && cmp) {
                pc = labelMap.get(args.getTargetName());
            }
            cmp = false;
        });

        instructionMap.put("je", (args, registers, history) -> {
            int result = Instruction.compare(last, registers);
            if (result == 0 && cmp) {
                pc = labelMap.get(args.getTargetName());
            }
            cmp = false;
        });

        instructionMap.put("jge", (args, registers, history) -> {
            int result = Instruction.compare(last, registers);
            if (result >= 0 && cmp) {
                pc = labelMap.get(args.getTargetName());
            }
            cmp = false;
        });

        instructionMap.put("jg", (args, registers, history) -> {
            int result = Instruction.compare(last, registers);
            if (result > 0 && cmp) {
                pc = labelMap.get(args.getTargetName());
            }
            cmp = false;
        });

        instructionMap.put("jle", (args, registers, history) -> {
            int result = Instruction.compare(last, registers);
            if (result <= 0 && cmp) {
                pc = labelMap.get(args.getTargetName());
            }
            cmp = false;
        });

        instructionMap.put("jl", (args, registers, history) -> {
            int result = Instruction.compare(last, registers);
            if (result < 0 && cmp) {
                pc = labelMap.get(args.getTargetName());
            }
            cmp = false;
        });
    }

    static Arguments newArguments(String[] args) {
        return new Arguments(args[0].replaceAll(";.*", ""),
                args.length > 1 ? args[1].replaceAll(";.*", "") : null);
    }

    public static String interpret(final String input) {
        Map<String, Integer> registers = new HashMap<>();
        done = false;
        labelMap = new HashMap<>();
        last = null;
        cmp = false;
        boolean skip = false;
        pc = 0;
        Stack<Integer> history = new Stack<>();
        resultBuilder = new StringBuilder();
        String[] inputArr = input.replaceAll(";.*", "").split("\n");
        parseLabels(inputArr);
        while (!done && pc < inputArr.length) {
            String[] instructionSplit = inputArr[pc].trim().split(",?\\s+");
            if (inputArr[pc].matches("\\s*.+:")) {
                pc++;
                skip = true;
                continue;
            } else if (instructionSplit[0].equals("ret")) {
                pc = history.pop();
                cmp = false;
            } else if (inputArr[pc].matches("^\\s*call\\s*.+")) {
                history.push(pc);
                pc = labelMap.get(instructionSplit[instructionSplit.length - 1]);
            } else if (!skip && !inputArr[pc].isBlank()) {
                processInstruction(inputArr, instructionSplit, registers, history);
            } else if (!history.isEmpty()) {
                pc = history.pop();
            }
            pc++;
        }
        return done ? resultBuilder.toString().trim() : null;
    }

    private static void parseLabels(String[] inputArr) {
        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = inputArr[i].trim();
            Matcher matcher = LABEL_PATTERN.matcher(inputArr[i]);
            while (matcher.find()) {
                labelMap.put(inputArr[i].substring(matcher.start(), matcher.end() - 1), i);
            }
        }
    }

    private static void processInstruction(String[] inputArr, String[] instructionSplit, Map<String, Integer> registers, Stack<Integer> history) {
        if (instructionSplit[0].equals("msg")) {
            processMessage(inputArr[pc], registers);
        } else if (instructionSplit[0].equals("end")) {
            done = true;
        } else if (inputArr[pc].matches("^\\s*cmp.*")) {
            last = newArguments(Arrays.copyOfRange(instructionSplit, 1, instructionSplit.length));
            cmp = true;
        } else if (!inputArr[pc].startsWith(";")) {
            Arguments arguments = newArguments(Arrays.copyOfRange(instructionSplit, 1, instructionSplit.length));
            instructionMap.get(instructionSplit[0]).apply(arguments, registers, history);
            last = arguments;
        }
    }

    private static void processMessage(String instruction, Map<String, Integer> registers) {
        String[] split = instruction.substring(3).trim().split("'");
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                if (!split[i].isBlank())
                    resultBuilder.append(registers.get(split[i].replaceAll(",", "").trim()));
            } else {
                if (!split[i].isBlank())
                    resultBuilder.append(split[i]);
                else
                    resultBuilder.append(",");
            }
        }
        resultBuilder = new StringBuilder(resultBuilder.toString().trim()).append("\n");
    }
}


