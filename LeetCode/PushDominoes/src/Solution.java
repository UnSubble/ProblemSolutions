class Solution {
    public String pushDominoes(String dominoes) {
        int[] res = new int[dominoes.length()];
        int p = 0, a = 0;
        for (int i = 0; i < res.length; i++) {
            char c = dominoes.charAt(i);
            if (c == 'R') {
                res[i] = 1;
                p = 1;
                a = 1;
            } else if (c == 'L') {
                p = 1;
                int j = i - 1;
                res[i] = -1;
                while (j >= 0 && dominoes.charAt(j) == '.') {
                    p++;
                    if (res[j] == p)
                        res[j] = 0;
                    else if (res[j] > p || res[j] == 0)
                        res[j] = -p;
                    j--;
                }
                p = 0;
                a = 0;
            } else {
                p+=a;
                res[i] = p;
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int c : res) {
            if (c > 0) {
                sb.append('R');
            } else if (c < 0) {
                sb.append('L');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}