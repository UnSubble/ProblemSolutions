
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

bool hasPathSum(struct TreeNode* root, int targetSum) {
    bool sum(struct TreeNode* root, int targetSum, int rootSum) {
        if (root == NULL)
            return false;
        if (root->left == NULL && root->right == NULL)
            return rootSum + root->val == targetSum;
        return sum(root->left, targetSum, rootSum + root->val) || 
            sum(root->right, targetSum, rootSum + root->val);
    } 
    return root != NULL && sum(root, targetSum, 0);
}
