
public class Solution {
    public String losingPlayer(int x, int y) {
        int i = 0;
        int m = 0;
        int j = 0;
        while (i <= x && j <= y) {  
            int n = 0;
            while (n < 115 && i < x) {
            if (n + 75 > 115)
                break;
            n += 75;
            i++;
            }    
            while (n < 115 && j < y) {
            if (n + 10 > 115)
                break;
            n += 10;
            j++;
            }
            if (n == 115)
                m++;
            else
                break;
        }
        return m % 2 == 0 ? "Bob":"Alice";
    }
}