
public class Solution {
    public int minSteps(String s, String t) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 97]++;
        }
        for (char c : t.toCharArray()) {
            arr[c - 97]--;
        }
        int res = 0;
        for (int c : arr) {
            res += Math.abs(c);
        }
        return res / 2;
    }
}