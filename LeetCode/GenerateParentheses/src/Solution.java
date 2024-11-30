import java.util.*;

public class Solution {
	List<String> result;
	
	public void generate(StringBuilder sb, int start, int n) {
		int length = sb.length();
		if (length == 2 * n) {
			result.add(sb.toString());
			return;
		}
		for (int i = start; i < length; i++) {
			StringBuilder temp = new StringBuilder(sb);
			temp.insert(i, "(").insert(i + 1, ")");
			generate(temp, i + 1, n);
		}
	}
	
	public List<String> generateParenthesis(int n) {
		result = new ArrayList<>();
		StringBuilder sb = new StringBuilder("()");
		generate(sb, 0, n);
		return result;
    }
}
