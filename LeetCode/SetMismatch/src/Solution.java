public class Solution {
    public int[] findErrorNums(int[] nums) {
        int count = 0;
        int[] arr = new int[nums.length];
        for (int c : nums) {
        	arr[c - 1]++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (arr[i] != 1) 
                count++;
        }
        int[] res = new int[count];
        int s_index = 0;
        int d_index = count / 2;
        for (int i = 0; i < nums.length; i++) {
            if (arr[i] > 1) 
                res[s_index++] = i + 1;
            else if (arr[i] < 1)
                res[d_index++] = i + 1;
        }
        return res;
    }
}