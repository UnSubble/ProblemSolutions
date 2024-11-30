import java.util.Stack;

public class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder());
        int index = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (ch == '(') {
                stack.push(new StringBuilder());
            } else if (ch == ')') {
                StringBuilder last = stack.pop();
                stack.peek().append(last.reverse());
            } else 
                stack.peek().append(ch);
            index++;
        }
        return stack.peek().toString();
    }

}
