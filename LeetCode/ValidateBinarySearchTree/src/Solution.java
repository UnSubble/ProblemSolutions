import java.util.*;
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

public class Solution {
	Queue<Integer> queue;
	
	public void sortTree(TreeNode root) {
		if (root.left != null) sortTree(root.left);
		queue.offer(root.val);
		if (root.right != null) sortTree(root.right);
	}
	
    public boolean isValidBST(TreeNode root) {
    	if (root == null || (root.left == null && root.right == null)) return true;
    	queue = new LinkedList<>();
    	sortTree(root);
    	int last = queue.poll();
    	while (!queue.isEmpty()) {
    		int val = queue.poll();
    		if (last >= val) return false;
    		last = val;
    	}
        return true;
    }
}