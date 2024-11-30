class Solution {
    public String countAndSay(int n) {
        if (n == 1)
            return "1";
        StringBuilder sb = new StringBuilder();
        String prev = countAndSay(n - 1);
        int index = 0;
        while (index < prev.length()) {
            int x = countNextSameDigits(prev, index);
            sb.append(x).append(prev.charAt(index));
            index += x;
        }
        return sb.toString();
    } 

    int countNextSameDigits(String str, int n) {
        char target = str.charAt(n);
        int total = 1;
        for (int i = n + 1; i < str.length(); i++) {
            if (target != str.charAt(i))
                return total;
            total++;
        }
        return total;
    }

    
}
