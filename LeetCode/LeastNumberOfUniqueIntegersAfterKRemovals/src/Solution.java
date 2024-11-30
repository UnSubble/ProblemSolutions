import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int c : arr) {
            int m = map.computeIfAbsent(c, x -> 0);
            map.put(c, m + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        list.sort(Comparator.naturalOrder());
        int index = 0;
        while (k >= 0) {
            if (index >= list.size())
                return 0;
            k -= list.get(index++);
        }
        return list.size() - index + 1;
    }
}