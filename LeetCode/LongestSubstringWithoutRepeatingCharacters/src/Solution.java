import java.util.Arrays;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] arr = new int[128];
        int start = 0;
        int max = 0;
        Arrays.fill(arr, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (arr[c] != -1) {
                max = Math.max(max, i - start);
                for (; start < i; start++) {
                    arr[s.charAt(start)] = -1;
                    if (s.charAt(start) == c) {
                        start++;
                        break;
                    }
                }
            }
            arr[c] = i;
        }
        max = Math.max(max, s.length() - start);
        return max;
    }
}