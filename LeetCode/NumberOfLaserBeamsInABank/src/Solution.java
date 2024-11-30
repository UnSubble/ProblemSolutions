
public class Solution {
    public int numberOfBeams(String[] bank) {
        int res = 0;
        int last = 0;
        for (String row : bank) {
            int n = 0;
            for (char c : row.toCharArray()) {
                n += c - 48;
            }
            if (n == 0)
                continue;
            res += last * n;
            last = n;
        }
        return res;
    }
}