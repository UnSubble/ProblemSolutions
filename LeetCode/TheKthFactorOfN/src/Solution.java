
public class Solution {
    public int kthFactor(int n, int k) {
        double sqrt = Math.sqrt(n);
        int count = 0;
        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                count++;
                if (i != sqrt)
                    count++;
            }
        }
        boolean last = (count % 2 == 0 ? count / 2 : count / 2 + 1) < k;
        if (last)
            k = count - k + 1;
        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                k--;
                if (k == 0) {
                    if (last)
                        return n / i;
                    else
                        return i;
                }
            }
        }
        return -1;
    }
}