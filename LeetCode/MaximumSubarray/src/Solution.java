
public class Solution {
    public int maxSubArray(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int rl = l;
        int rr = nums.length;
        int ls = 0;
        int rs = 0;
        int max = Integer.MIN_VALUE;
        for (int c : nums) {
            if (max < c)
                max = c;
        }

        while (l <= r) {
            if (nums[l] + ls < 0){
                rl = l + 1;
                ls = 0;
            } else {
                ls += nums[l];
                max = Math.max(max, ls);
            }
            if (nums[r] + rs < 0){
                rr = r;
                rs = 0;
            } else {
                rs += nums[r];
                max = Math.max(max, rs);
            }
            l++;
            r--;
        }
        int sum = 0;
        for (int i = rl; i < rr; i++) {
            sum += nums[i];
        }
        return rl >= rr ? (rr < nums.length && nums[rr] > max ? nums[rr] : max) : Math.max(sum, max);
    }

}