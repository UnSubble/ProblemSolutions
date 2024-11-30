
public class Solution {
    public String minWindow(String s, String t) {
        int[] arr = new int['z' - 'A' + 1];
        for (char c : t.toCharArray())
            arr[c - 'A']++;
        int[] lfound = new int['z' - 'A' + 1];
        int start = 0;
        int length = Integer.MAX_VALUE;
        int l = 0;
        int il = 0;
        while (l < s.length()) {
            char lc = s.charAt(l);
            if (arr[lc - 'A'] > 0) {
                lfound[lc - 'A']++;
            }
            boolean lb = true;
            for (int i = 0; i < arr.length; ++i) {
                if (lfound[i] < arr[i]) {
                    lb = false;
                    break;
                }
            }
            if (lb) {
                char ilc = s.charAt(il);
                while (il < l && (arr[ilc - 'A'] == 0 || arr[ilc - 'A'] < lfound[ilc - 'A'])) {
                    if (arr[ilc - 'A'] < lfound[ilc - 'A'])
                        lfound[ilc - 'A']--;
                    ++il;
                    ilc = s.charAt(il);
                }
                if (length > l - il + 1) {
                    length = l - il + 1;
                    start = il;
                }
            }
            l++;
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }
}