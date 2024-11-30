import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        int max = 0;
        for (int c : nums) {
            if (max < c)
                max = c;
        }
        int[] arr = new int[max];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        result.add(sub);
        int index = 0;
        for (int num : nums) {
            index = arr[num - 1];
            arr[num - 1]++;
            if (result.size() <= index) {
                sub = new ArrayList<>();
                sub.add(num);
                result.add(sub);
            } else {
                result.get(index).add(num);
            }
        }
        return result;
    }
}