class Solution {
    public int rob(int[] nums) {
        int index = 2;
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        while (index < nums.length) {
            dp[index] = dp[index - 2] + nums[index];
            if (index > 2) {
                dp[index] = Math.max(dp[index - 3] + nums[index], dp[index]);
            }
            index++;
        }
        return Math.max(dp[dp.length - 1], dp[dp.length - 2]);
    }
}
