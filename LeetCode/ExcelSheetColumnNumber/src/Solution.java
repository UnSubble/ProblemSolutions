
public class Solution {
	public int titleToNumber(String columnTitle) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int num = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
        	num += Math.pow(26, columnTitle.length() - i - 1) * (alphabet.indexOf(columnTitle.charAt(i)) + 1);
        }
        return num;	
    }
	public static void main(String[] args) {
		System.out.println(new Solution().titleToNumber("AB"));
	}
}
