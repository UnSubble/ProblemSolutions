public class Solution {
    public String intToRoman(int num) {
        char[] arr1 = {'I', 'X', 'C', 'M'};
        char[] arr2 = {'V', 'L', 'D'};
        StringBuilder res = new StringBuilder();
        String numstr = String.valueOf(num);
        for (int i = 0; i < numstr.length(); i++) {
            char c = numstr.charAt(i);
            int index = numstr.length() - 1 - i;
            if (c <= '3') {
            for (int j = 0; j < c - 48; j++) 
                res.append(arr1[index]);
        }
        else if (c >= '4' && c <= '8') {
            if (c == '4')
                res.append(arr1[index]);
            res.append(arr2[index]);
            for (int j = 5; j < c - 48; j++) 
                res.append(arr1[index]);
        }
        else if (c == '9') {
            res.append(arr1[index]);
            res.append(arr1[index + 1]);
        }
        }
        return res.toString();
    }
}