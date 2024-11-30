
public class Solution {
	int search(int[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;
		while (start <= end) {
			int middle = (start + end) >> 1;
			int cmp = arr[middle];
			if (cmp < target)
				start = middle + 1;
			else if (target == cmp)
				return middle;
			else
				end = middle - 1;
		}
		return -1;
	}
	
	int find(int[] arr, int f, int s) {
		int index = search(arr, f + s);
		if (index == -1)
			return 2;
		return find(arr, s, arr[index]) + 1;
	}
	
	public int lenLongestFibSubseq(int[] arr) {
		int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
        	for (int j = i + 1; j < arr.length; j++) {
        		int r = find(arr, arr[i], arr[j]);
        		if (r > 2 && max < r)
        			max = r;
        	}
        }
        return max;
    }
}