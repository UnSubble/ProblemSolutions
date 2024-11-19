import java.util.Stack;

class Solution {
    public boolean parseBoolExpr(String expression) {
        StringBuilder exBuilder = new StringBuilder(expression);
        Stack<Integer> indexes = new Stack<>();
        char state = '\0';
        while (exBuilder.length() > 1) {
            for (int i = 0; i < exBuilder.length(); i++) {
                char c = exBuilder.charAt(i);
                if (c == '(')
                    indexes.push(i);
                switch (state) {
                    case '&' -> {
                        if (c == 'f') {
                            replace(exBuilder, i, indexes.pop(), "f");
                            state = '\0';
                        } else if (c == ')') {
                            replace(exBuilder, i - 1, indexes.pop(), "t");
                            state = '\0';
                        }
                    }
                    case '|' -> {
                        if (c == 't') {
                            replace(exBuilder, i, indexes.pop(), "t");
                            state = '\0';
                        } else if (c == ')') {
                            replace(exBuilder, i - 1, indexes.pop(), "f");
                            state = '\0';
                        }
                    }
                    case '!' -> {
                        if (c == 't' || c == 'f') {
                            replace(exBuilder, i, indexes.pop(), c == 't' ? "f" : "t");
                            state = '\0';
                        }
                    }
                }
                if (c == '!' || c == '&' || c == '|')
                    state = c;
            }
        }
        return exBuilder.toString().equals("t");
    }

    private void replace(StringBuilder exBuilder, int i, int start, String toReplace) {
        int paren = 1;
        int j = i + 1;
        for (; j < exBuilder.length(); j++) {
            char ch = exBuilder.charAt(j);
            if (paren == 0)
                break;
            if (ch == '(')
                paren++;
            if (ch == ')')
                paren--;
        }
        exBuilder.delete(start - 1, j).insert(start - 1, toReplace);
    }
}