
public class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        StringBuilder sb = new StringBuilder();
        int length = encodedText.length() / rows;
        for (int i = 0; i < length; i++) {
            for (int j = i, k = 0; j < length && k < rows; j++, k++) {
                sb.append(encodedText.charAt(j + k * length));
            }
        }
        return sb.toString().stripTrailing();
    }
}