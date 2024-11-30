
public class Solution {
    public int myAtoi(String s) {
        long res = 0L;
        boolean isNegative = false;
        boolean eDigit = false;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9')
                eDigit = true;
            if (!eDigit && c == ' ')
                continue;
            if (!eDigit && (c == '+' || c == '-')) {
                eDigit = true;
                isNegative = c == '-';
                continue;
            }
            if (c < '0' || c > '9')
                break;
            else {
                res *= 10;
                res += c - '0';
                if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
                    break;
            }

        }
        if (isNegative)
            res = -res;
        return Math.clamp(res, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}