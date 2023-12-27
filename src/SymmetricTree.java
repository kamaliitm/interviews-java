import java.util.LinkedList;
import java.util.Queue;

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

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        return compareNodes(root.left, root.right);

    }

    private boolean compareNodes(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) return true;

        if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
            return false;
        }

        return compareNodes(leftNode.left, rightNode.right) && compareNodes(leftNode.right, rightNode.left);
    }


}
