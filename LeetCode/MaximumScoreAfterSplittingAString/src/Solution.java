public class Solution {
    public int maxScore(String s) {
        int res = 0;
        int zeros = 0;
        int ones = 0;
        int length = s.length();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '0')
                zeros++;
            else
                ones++;
            arr[i] = zeros;
        }
        for (int i = 0; i < length - 1; i++) {
            int zero = arr[i];
            res = Math.max(res, zero + ones - (i + 1 - zero));
        }
        return res;
    }
}