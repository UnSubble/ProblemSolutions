
public class Solution {
    public int minOperations(int[] nums) {
        int max = 0;
        for (int num : nums) {
            if (max < num)
                max = num;
        }
        int[] arr = new int[max];
        for (int num : nums) {
            arr[num - 1]++;
        }
        int res = 0;
        for (int c : arr) {
            while (c > 1) {
                int d;
                if (c % 3 == 0) {
                    d = c / 3;
                    c -= d * 3;
                } else {
                    d = 1;
                    c -= d * 2;
                }
                res += d;
            }
            if (c == 1)
                return -1;
        }
        return res;
    }
}