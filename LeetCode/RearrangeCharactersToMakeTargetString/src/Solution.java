
public class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        int res = 100;
        int[] rra = new int[26];
        for (char c : target.toCharArray()) {
            rra[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (rra[i] == 0)
                continue;
            res = Math.min(res, arr[i] / rra[i]);
        }
        return res;
    }
}