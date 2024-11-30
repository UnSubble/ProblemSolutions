
public class Solution {
    public int minCost(String colors, int[] neededTime) {
        char last = colors.charAt(0);
        int res = 0;
        int lastMax = neededTime[0];
        for (int i = 1; i < colors.length(); i++) {
            char c = colors.charAt(i);
            if (c == last) {
                if (neededTime[i] < lastMax) {
                    res += neededTime[i];    
                } else {
                    res += lastMax;
                    lastMax = neededTime[i];
                } 
            } else {
                last = c;
                lastMax = neededTime[i];
            }
            
        }
        return res;
    }
}

