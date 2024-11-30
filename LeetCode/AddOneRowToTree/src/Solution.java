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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1 || root == null) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        if (root.right != null) 
            addOneRow(root.right, val, depth - 1);
        if (root.left != null)
            addOneRow(root.left, val, depth - 1);
        if (depth == 2) {
            TreeNode temp = null;
            if (root.right != null)
                temp = root.right;
            root.right = new TreeNode(val);
            root.right.right = temp;
            temp = null;
            if (root.left != null)
                temp = root.left;
            root.left = new TreeNode(val);
            root.left.left = temp;
        }
        return root;
    }

}