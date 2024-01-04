public class MaxPathSum {

    int maxPath;

    MaxPathSum() {
        this.maxPath = Integer.MIN_VALUE;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return root.val;

        getMathPath(root);

        return maxPath;
    }

    private int getMathPath(TreeNode node) {
        if (node == null) return 0;

        int leftMaxPath = Math.max(getMathPath(node.left), 0);
        int rightMaxPath = Math.max(getMathPath(node.right), 0);

        int currMaxPath = node.val + leftMaxPath + rightMaxPath;
        maxPath = Math.max(maxPath, currMaxPath);

        return node.val + Math.max(leftMaxPath, rightMaxPath);
    }



}
