import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        int length = String.valueOf(high).length();
        for (int i = String.valueOf(low).length(); i <= length; i++) {
            for (int j = 1; j < 10; j++) {
                int seq = j;
                StringBuilder sb = new StringBuilder();
                boolean isOkay = true;
                for (int k = 0; k < i; k++) {
                    if (seq + k > 9) {
                        isOkay = false;
                        break;
                    }
                    sb.append((char)(seq + k + '0'));
                }
                if (!isOkay)
                    continue;
                int val = Integer.parseInt(sb.toString());
                if (val >= low && high >= val)
                    list.add(val); 
            }
        }
        return list;
    }
}