import java.util.Arrays;

public class Solution {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        long result = 0;
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int v = 1, h = 1;
        int hIndex = m - 2, vIndex = n - 2;
        while (hIndex >= 0 || vIndex >= 0) {
            long max = 0;
            if (hIndex >= 0)
                max = horizontalCut[hIndex];
            if (vIndex >= 0) {
                max = Math.max(verticalCut[vIndex], max);
                if (max == verticalCut[vIndex]) {
                    vIndex--;
                    result += v * max;
                    h++;
                } else {
                    hIndex--;
                    result += h * max;
                    v++;
                }
            } else {
                hIndex--;
                result += h * max;
                v++;
            }
        }
        return result;
    }
}