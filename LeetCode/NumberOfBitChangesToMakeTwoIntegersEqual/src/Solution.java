
public class Solution {
    public int minChanges(int n, int k) {
        if (n == k)
            return 0;
        int res = 0;
        int nlength = 0;
        int[] narr = new int[20];
        while (n > 0) {
            narr[nlength] = n % 2;
            n >>= 1;
            nlength++;
        }
        int klength = 0;
        int[] karr = new int[20];
        while (k > 0) {
            karr[klength] = k % 2;
            k >>= 1;
            klength++;
        }
        if (klength > nlength)
            return -1;
        for (int i = 0; i < nlength; i++) {
            if (narr[i] == 0 && karr[i] == 1)
                return -1;
            if (narr[i] == 1 && karr[i] == 0)
                res++;
        }
        return res;
    }
}