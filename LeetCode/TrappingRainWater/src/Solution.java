
public class Solution {
    public int trap(int[] height) {
        int right = 0;
        int left = 0;
        int total = 0;
        int ttotal = 0;
        int l = 0, r = height.length - 1;
        int result = 0;
        while (l < height.length) {
            int t = right - height[r];
            int tt = left - height[l];
            if (t >= 0)
                total += t;
            else {
                right = height[r];
                result += total;
                total = 0;
            }
            if (tt > 0)
                ttotal += tt;
            else {
                left = height[l];
                result += ttotal;
                ttotal = 0;
            }
            r--;
            l++;
        }
        return result;
    }

}