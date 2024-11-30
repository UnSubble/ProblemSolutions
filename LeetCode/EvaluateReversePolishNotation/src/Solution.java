import java.util.Stack;

public class Solution {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        while (index < tokens.length) {
        	String str = tokens[index++];
        	int num1 = 0;
        	int num2 = 0;
        	if (!isNum(str)) {
        		num1 = stack.pop();
        		num2 = stack.pop();
        	} else {
        		stack.push(Integer.parseInt(str));
        		continue;
        	}
        	if (str.equals("*")) 
        		num2 *= num1;
        	else if (str.equals("/"))
        		num2 /= num1;
        	else if (str.equals("+"))
        		num2 += num1;
        	else
        		num2 -= num1;
        	stack.push(num2);
        }
        return stack.pop();
    }
    
    private boolean isNum(String str) {
    	return !(str.equals("+") || str.equals("-") || 
    			str.equals("*") || str.equals("/"));
    }
}