import java.util.Arrays;

public class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int[][] res = new int[nums.length / 3][];
        int index = 0;
        for (int i = 2; i < nums.length; i += 3) {
            if (nums[i] - nums[i - 2] <= k) {
                int[] sub = new int[3];
                sub[0] = nums[i - 2];
                sub[1] = nums[i - 1];
                sub[2] = nums[i];
                res[index++] = sub;
            }
            else 
                return new int[0][];             
            
        }
        return res;
    }
}