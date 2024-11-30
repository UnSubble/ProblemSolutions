
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            for (int j = 0; j < obstacleGrid.length; j++) {
                if (obstacleGrid[j][i] == 1) 
                    obstacleGrid[j][i] = 0;
                else if (j == 0 && i == 0)
                    obstacleGrid[0][0] = 1;
                else {
                    int x = j > 0 ? obstacleGrid[j - 1][i] : 0;
                    int y = i > 0 ? obstacleGrid[j][i - 1] : 0;
                    obstacleGrid[j][i] = x + y;
                }
            }
        }
        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
}