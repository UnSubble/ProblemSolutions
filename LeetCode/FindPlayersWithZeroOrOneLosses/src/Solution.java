import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<Integer>> findWinners(int[][] matches) { 
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> w = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        res.add(w);
        res.add(l);

        Map<Integer, int[]> map = new HashMap<>();

        for (int[] match : matches) {
            int[] winner = map.computeIfAbsent(match[0], x -> new int[2]);
            winner[0]++;
            winner[1]++;
            int[] loser = map.computeIfAbsent(match[1], x -> new int[2]);
            loser[0]++;
        }
        
        for (int c : map.keySet()) {
            if (!map.containsKey(c))
                continue;
            int[] player = map.get(c);
            if (player[0] == player[1])
                w.add(c);
            else if (player[0] == player[1] + 1)
                l.add(c);
        }

        w.sort(Comparator.naturalOrder());
        l.sort(Comparator.naturalOrder());

        return res;
    }
}