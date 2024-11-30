import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

class Trie {
    Trie[] next = new Trie[26];
    List<int[]> point = new ArrayList<>();
}

public class Solution {
    static Trie root;

    static void define(List<String> genes, List<Integer> health) {
        root = new Trie();
        for (int i = 0; i < genes.size(); i++) {
            Trie curr = root;
            for (char c : genes.get(i).toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    Trie last = new Trie();
                    curr.next[c - 'a'] = last;
                    curr = last;
                } else
                    curr = curr.next[c - 'a'];
            }
            curr.point.add(new int[] {i, health.get(i)});
        }
    }

    static long calc(int first, int last, String d) {
        List<Trie> list = new ArrayList<>();
        long point = 0;
        for (int i = 0; i < d.length(); i++) {
            char c = d.charAt(i);
            if (root.next[c - 'a'] != null)
                list.add(root);
            int index = list.size() - 1;
            while (index >= 0) {
                Trie target = list.get(index).next[c - 'a'];
                if (target != null) {
                    for (int[] pointEntry : target.point) {
                        if (pointEntry[0] >= first && pointEntry[0] <= last)
                            point += pointEntry[1];
                    }
                    list.add(target);
                }
                list.remove(index);
                index--;
            }
        }
        return point;
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> genes = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        List<Integer> health = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());
            
        define(genes, health);

        int s = Integer.parseInt(bufferedReader.readLine().trim());
        
        long max = 0;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < s; i++) {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int first = Integer.parseInt(firstMultipleInput[0]);

                int last = Integer.parseInt(firstMultipleInput[1]);

                String d = firstMultipleInput[2];
                
                long res = calc(first, last, d);
                max = Math.max(res, max);
                min = Math.min(res, min);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };
        
        System.out.println(min + " " + max);

        bufferedReader.close();
    }
}
