import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int[] count = new int[2001];
        for (int c : arr) {
            count[(c + 2001) % 2001]++;
        }
        Set<Integer> set = new HashSet<>();
        for (int c : count) {
            if (c == 0)
                continue;
            else if (set.contains(c))
                return false;
            else
                set.add(c);
        }
        return true;
    }
}