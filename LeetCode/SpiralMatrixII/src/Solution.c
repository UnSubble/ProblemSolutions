int** generateMatrix(int n, int* returnSize, int** returnColumnSizes) {
        int m = 0;
        int last = 0;
        int** arr = (int**) malloc(sizeof(int*) * n);
        *returnColumnSizes = (int*) malloc(sizeof(int*) * n);
        *returnSize = n;
        for (int i = 0; i < n; i++) {
            arr[i] = (int*) malloc(sizeof(int) * n);
            (*returnColumnSizes)[i] = n;
        }
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
