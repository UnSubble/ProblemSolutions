import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int sublength = matrix[0].length;
        int length = matrix.length;
        int maxlength = Math.min(length, sublength);
        maxlength = maxlength % 2 == 0 ? (maxlength / 2) : (maxlength / 2 + 1);
        for (int i = 0; i < maxlength; i++) {
            for (int j = i; j < sublength - i; j++) 
                list.add(matrix[i][j]);
            for (int j = i + 1; j < length - i; j++) 
                list.add(matrix[j][sublength - 1 - i]);
            if (list.size() == length * sublength)
                break;
            for (int j = sublength - 2 - i; j >= i; j--) 
                list.add(matrix[length - 1 - i][j]);
            for (int j = length - 2 - i; j > i; j--) 
                list.add(matrix[j][i]);
        }
        return list;
    }
}