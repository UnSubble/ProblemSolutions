import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }
    TreeNode(int val) { 
    	this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class Solution {
    List<Integer> list = new ArrayList<>();

    void rec(TreeNode root) {
        if (root == null) return;
        if (root.left != null) rec(root.left);
        list.add(root.val);
        if (root.right != null) rec(root.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        rec(root);
        return list;
    }
}