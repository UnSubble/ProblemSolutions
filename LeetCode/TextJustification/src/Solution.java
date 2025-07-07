import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int index = 0;
        List<String> list = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        while (index < words.length) {
            int j = index;
            int length = 0;
            int realLength = 0;
            while (j < words.length && length + realLength + words[j].length() <= maxWidth) {
                length++;
                realLength += words[j].length();
                j++;
            }
            if (realLength == 0) {
                realLength = words[index].length();
            }
            int w = maxWidth - realLength;
            for (; index < j; index++) {
                length--;
                int space = (int)Math.ceil(w / (double)length);
                if (realLength == 0 || j - index == 1)
                    space = 0;
                else if (j == words.length)
                    space = 1;
                result.append(words[index]).append(" ".repeat(Math.min(10000, Math.max(space, 0))));
                w -= space;
            }
            result.append(" ".repeat(Math.max(maxWidth - result.length(), 0)));
            list.add(result.toString());
            result.setLength(0);
        }
        return list;
    }
}
