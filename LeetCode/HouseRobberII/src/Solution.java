class Solution {
    public int rob(int[] nums) {
        if (nums.length < 3)
            return Math.max(nums[0], nums[nums.length - 1]);
        return Math.max(getMaxFromRange(nums, nums[0], nums.length - 1),
                getMaxFromRange(nums, 0, nums.length));
    }

    int getMaxFromRange(int[] nums, int first, int end) {
        int[] dp = new int[nums.length];
        dp[0] = first;
        dp[1] = nums[1];
        for (int i = 2; i < end; i++) {
            dp[i] = nums[i] + dp[i - 2];
            if (i > 2) {
                dp[i] = Math.max(dp[i - 3] + nums[i], dp[i]);
            }
        }
        return Math.max(dp[end - 1], dp[end - 2]);
    }
}
