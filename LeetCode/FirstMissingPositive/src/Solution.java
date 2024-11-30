import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int firstMissingPositive(int[] nums) {
    	Arrays.sort(nums);
    	int find = 0;
    	int num = 0;
    	while (find >= 0) {
    		find = Arrays.binarySearch(nums, ++num);
    	}
    	return num;
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().firstMissingPositive(new int[] {3,4,-1,1}));
	}
}
