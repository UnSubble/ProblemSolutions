import java.util.ArrayList;
import java.util.List;

public class Solution {
    private String[] buttons = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty())
            return res;
        factorial(digits, 0, res, new StringBuilder());
        return res;
    }

    private void factorial(String digits, int pos, List<String> resultList, StringBuilder str) {
        if (digits.length() == pos) {
            resultList.add(str.toString());
            return;
        }
        String button = buttons[digits.charAt(pos) - '2'];
        for (int i = 0; i < button.length(); i++) {
            factorial(digits, pos + 1, resultList, new StringBuilder(str).append(button.charAt(i)));
        }
    }
}

