//class TreeNode {
//      int val;
//      TreeNode left;
//      TreeNode right;
//      TreeNode() {}
//      TreeNode(int val) { this.val = val; }
//      TreeNode(int val, TreeNode left, TreeNode right) {
//          this.val = val;
//          this.left = left;
//          this.right = right;
//      }
//}

public class DiameterBinaryTree {

    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        diameterHelper(root);

        return diameter;
    }

    private int diameterHelper(TreeNode root) {
        if (root == null) return 0;

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        diameter = Math.max(diameter, leftDiameter+rightDiameter);

        return 1 + Math.max(leftDiameter, rightDiameter);
    }


}


