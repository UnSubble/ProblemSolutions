public class Solution {
    public String smallestNumber(String pattern) {
        int[] arr = new int[pattern.length() + 1];
        arr[0] = 1;
        int maxNum = 1;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == 'D') {
                int j = i;
                char target = pattern.charAt(j);
                char sameTarget = target;
                while (target == sameTarget) {
                    arr[j]++;
                    maxNum = Math.max(arr[j], maxNum);
                    j--;
                    if (j < 0)
                        break;
                    target = pattern.charAt(j);
                } 
                arr[i + 1] = arr[i] - 1;
            } else {
                arr[i + 1] = maxNum + 1;
                maxNum++;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int num : arr) {
            res.append(num);
        }
        return res.toString();
    }
}