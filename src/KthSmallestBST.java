public class KthSmallestBST {

    public int kthSmallest(TreeNode root, int k) {
        Integer kth = kThSmallestRecursive(root, k);

        return kth == null ? -1 : kth;
    }

    private Integer kThSmallestRecursive(TreeNode root, int k) {
        if (root == null) return null;
        Integer left = kThSmallestRecursive(root.left, k);
        if (left == null) { // root is smallest, repeat for k-1 times
            k--;
            if (k == 0) return root.val;
            return kThSmallestRecursive(root.right, k);
        }

        return left;
    }
}
