import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	  List<List<Integer>> result;
		void perm(List<Integer> resultList, int length, int n, int[] nums) {
			if (n == 0) {
				result.add(resultList);
				return;
			}
			for (int i = 0; i < length; i++) {
				List<Integer> temp = new ArrayList<>(resultList);
				if (temp.contains(nums[i])) continue;
				temp.add(nums[i]);
				perm(temp, length, n - 1, nums); 
			}
		}
		
	    public List<List<Integer>> permute(int[] nums) {
	    	result = new ArrayList<>();
			List<Integer> temp = new ArrayList<>();
			perm(temp, nums.length, nums.length, nums);
	    	return result;
	    }
	    public static void main(String[] args) {
			System.out.println(new Solution().permute(new int[] {1,2,3}));
		}
}