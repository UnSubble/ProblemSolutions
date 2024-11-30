
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int[] last = new int[matrix[0].length];
        int res = 0;
        for (char[] sub : matrix) {
            for (int i = 0; i < sub.length; i++) {
                if (sub[i] == '0')
                    last[i] = 0;
                else
                    last[i]++;
            }
            res = Math.max(res, calc(last));
        }
        return res;
    }

    private int calc(int[] last) {
        int max = 0;
        for (int i = 0; i < last.length; i++) {
            if (last[i] == 0)
                continue;
            int n = last[i];
            int curr = n;
            for (int j = i + 1; j < last.length; j++) {
                if (last[j] == 0)
                    break;
                if (n > last[j]) {
                    max = Math.max(curr, max);
                    n = last[j];
                    curr = n * (j - i);
                }
                curr += n;
            }
            max = Math.max(curr, max);
        }
        return max;
    }
}