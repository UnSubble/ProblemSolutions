class Solution {
    public int[][] generateMatrix(int n) {
        int m = 0;
        int last = 0;
        int[][] arr = new int[n][n];
        arr[0][0] = 1;
        while (m < n / 2.0) {
            for (int i = m; i < n - m - 1; i++) {
                arr[m][i] = ++last;
            }
            for (int i = m; i < n - m; i++) {
                arr[i][n - m - 1] = ++last;
            }
            for (int i = n - m - 2; i >= m; i--) {
                arr[n - m - 1][i] = ++last;
            }
            for (int i = n - m - 2; i > m; i--) {
                arr[i][m] = ++last;
            }
            m++;
        }

        return arr;
    }
}