
public class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            int freq = prices[i] - prices[i - 1];
            if (freq > 0)
                res += freq;
        }
        return res;
    }
}