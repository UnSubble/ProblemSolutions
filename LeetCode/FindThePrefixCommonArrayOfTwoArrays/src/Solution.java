import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] findThePrefixCommonArray(int[] a, int[] b) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i],0) + 1);
            map.put(b[i], map.getOrDefault(b[i],0) + 1);
            if (a[i] != b[i] && map.get(a[i]) == 2)
                total++;
            if (a[i] != b[i] && map.get(b[i]) == 2)
                total++;
            if (a[i] == b[i])
                total++;
            res[i] = total;
        }
        return res;
    }
}
