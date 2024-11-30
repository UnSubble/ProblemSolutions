import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int sumNumbers(TreeNode root) {
        int total = 0;
        for (String str : getNums(root))
            total += Integer.parseInt(str);
        return total;
    }

    List<String> getNums(TreeNode node) {
        List<String> list = new ArrayList<>();
        if (node.left == null && node.right == null)
            list.add(String.valueOf(node.val));
        if (node.left != null) {
            for (String str : getNums(node.left)) {
                StringBuilder cpy = new StringBuilder();
                cpy.append(node.val).append(str);
                list.add(cpy.toString());
            }
        }
        if (node.right != null) {
            for (String str : getNums(node.right)) {
                StringBuilder cpy = new StringBuilder();
                cpy.append(node.val).append(str);
                list.add(cpy.toString());
            }
        }
        return list;
    }
}