import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean isHappy(int n) {
        String value = String.valueOf(n);
        Map<Integer, Integer> set = new HashMap<>();
        while (!set.containsKey(n)) {
            set.put(n, 1);
            n = 0;
            for (char c : value.toCharArray())
                n += (c - '0') * (c - '0');
            value = String.valueOf(n);
        }
        return n == 1;
    }
}
