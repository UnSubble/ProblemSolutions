import java.util.Arrays;

public class Solution {
    int count = 0;

    public int totalNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        dfs(queens, 0);
        return count;
    }

    private void dfs(int[] queens, int row) {
        if (row == queens.length) {
            count++;
            return;
        }
        for (int i = 0; i < queens.length; i++) {
            if (isValid(queens, row, i)) {
                queens[row] = i;
                dfs(queens, row + 1);
                queens[row] = -1;
            }
        }
    }

    private boolean isValid(int[] queens, int r, int i) {
        for (int j = 0; j < r; j++) {
            if (queens[j] == i || Math.abs(queens[j] - i) == Math.abs(j - r)) {
                return false;
            }
        }
        return true;
    }

}