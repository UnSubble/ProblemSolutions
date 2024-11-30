
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

int sumOfLeftLeaves(struct TreeNode* root) {
    int res = 0;
    if (root->left != NULL) {
        if (root->left->left == NULL && root->left->right == NULL)
            res += root->left->val;
        else
            res += sumOfLeftLeaves(root->left);
    }
    if (root->right != NULL)
        res += sumOfLeftLeaves(root->right);
    return res;
}
