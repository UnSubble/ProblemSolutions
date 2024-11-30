
public class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int hor = -matrix[0].length + 1;
        int ver = matrix.length - 1;
        while (++hor < ver) {
            int x = hor < 0 ? -hor : 0;
            int y = hor > 0 ? hor : 0;
            int num = matrix[y][x];
            while (++x < matrix[0].length && ++y < matrix.length) {
                if (num != matrix[y][x])
                    return false;
            }
        }
        return true;
    }
}
