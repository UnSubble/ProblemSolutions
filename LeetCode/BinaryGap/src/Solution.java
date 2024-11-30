public class Solution {
    public int binaryGap(int n) {
        int res = 0;
        int last = -1;
        while (n > 0) {
            double pow = 0.5;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                if (pow * 2 > n) {
                    if (last != -1)
                        res = Math.max(last - i + 1, res);
                    last = i - 1;
                    n -= (int)pow;
                    break;
                }
                pow *= 2;
            }
        }
        return res;
    }
}
