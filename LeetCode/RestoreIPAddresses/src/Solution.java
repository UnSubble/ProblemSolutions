import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    List<String> ipAddresses;

    public List<String> restoreIpAddresses(String s) {
        int[] arr = s.chars().map(x -> x - '0').toArray();
        ipAddresses = new ArrayList<>();
        for (int i = 0; i < arr.length - 3; i++) {
            StringBuilder sb = new StringBuilder();
            if (!isValidNum(arr, 0, i + 1)) {
                continue;
            }
            for (int j = 0; j <= i; j++) {
                sb.append(arr[j]);
            }
            sb.append(".");
            restoreIpRecursively(arr, i + 1, 1, sb);
        }
        return ipAddresses;
    }

    private void restoreIpRecursively(int[] arr, int i, int d, StringBuilder builder) {
        if (d == 4) {
            ipAddresses.add(builder.deleteCharAt(builder.length() - 1).toString());
            return;
        }
        LinkedList<Integer> validIndexes = new LinkedList<>();
        if (d < 3) {
            for (int j = i; j < arr.length - 3 + d; j++) {
                if (isValidNum(arr, i, j + 1))
                    validIndexes.add(j);
            }
        } else if (isValidNum(arr, i, arr.length)) {
            validIndexes.add(arr.length - 1);
        }
        while (!validIndexes.isEmpty()) {
            int index = validIndexes.remove();
            StringBuilder newBuilder = new StringBuilder(builder.toString());
            for (int j = i; j <= index; j++) {
                newBuilder.append(arr[j]);
            }
            newBuilder.append(".");
            restoreIpRecursively(arr, index + 1, d + 1, newBuilder);
        }
    }

    boolean isValidNum(int[] arr, int begin, int end) {
        int num = 0;
        int dc = 1;
        for (int i = begin; i < end; i++) {
            int newNum = num * 10 + arr[i];
            int newDc = digit(newNum);
            if (newDc < dc || newNum > 255) {
                return false;
            }
            num = newNum;
            dc++;
        }
        return true;
    }

    int digit(int n) {
        int dc = 1;
        while (n > 9) {
            n = n / 10;
            dc++;
        }
        return dc;
    }
}