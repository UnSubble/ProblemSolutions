import java.util.Arrays;

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
    void insert(TreeNode root, int num) {
        if (root.val > num) {
            if (root.left == null) root.left = new TreeNode(num);
            else insert(root.left, num);
        } else {
            if (root.right == null) root.right = new TreeNode(num);
            else insert(root.right, num);
        }
    }
    void findMiddle(TreeNode root, int[] nums) {
    	if (nums.length == 0) return;
    	insert(root, nums[nums.length / 2]);
    	findMiddle(root, Arrays.copyOfRange(nums, 0, nums.length / 2));
    	findMiddle(root, Arrays.copyOfRange(nums, nums.length / 2 + 1, nums.length));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        findMiddle(root, Arrays.copyOfRange(nums, 0, nums.length / 2));
        findMiddle(root, Arrays.copyOfRange(nums, nums.length / 2 + 1, nums.length));
        return root;
    }
}
