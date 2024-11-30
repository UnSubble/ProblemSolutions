import java.util.Arrays;

public class Solution {
    public int uniquePaths(int m, int n) {
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 1; i < m; i++) {
            int last = 1;
            for (int j = 1; j < n; j++) {
                arr[j] += last;
                last = arr[j];
            }
        }
        return arr[n - 1];
    }
}