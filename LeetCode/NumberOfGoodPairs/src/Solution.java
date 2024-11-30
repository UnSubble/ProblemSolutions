import java.util.*;
public class Solution {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int c : nums) {
            int n = map.get(c) == null ? 0 : map.get(c);
            if (n > 1)
                res -= n * (n - 1) / 2;
            map.put(c, n + 1);
            res += n * (n + 1) / 2;
        }
        return res;
    }
}