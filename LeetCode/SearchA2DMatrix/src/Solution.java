
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] n : matrix) {
            if (n[n.length - 1] >= target)
                return contains(n, target);
        }
        return false;
    }

    boolean contains(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) >> 1;
            int toCompare = arr[middle];
            if (toCompare > target) 
                end = middle - 1;
            else if (toCompare == target)
                return true;
            else
                start = middle + 1;
        }
        return false;
    }
}