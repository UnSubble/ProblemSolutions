import java.util.*;

public class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i]))
                map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        Comparator<List<Integer>> sorter = (x,y) ->  y.size() - x.size();
        List<List<Integer>> list = new ArrayList<>(map.values());
        Collections.sort(list, sorter);
        int max = list.get(0).size();
        int length = nums.length;
        for (List<Integer> sub : list) {
            if (max != sub.size())
                break;
            length = Math.min(length, sub.get(sub.size() - 1) - sub.get(0) + 1);
        }
        return length;
    }
}