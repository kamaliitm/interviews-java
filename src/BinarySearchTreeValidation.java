
import java.util.LinkedList;
import java.util.Queue;

//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode() {
//    }
//
//    TreeNode(int val) {
//        this.val = val;
//    }
//
//    TreeNode(int val, TreeNode left, TreeNode right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//}
//
//enum RangeType {
//    MIN,
//    MAX;
//}
//
//class RangeValue {
//    int val;
//    boolean isInclusive;
//
//    RangeValue(int val, boolean isInclusive) {
//        this.val = val;
//        this.isInclusive = isInclusive;
//    }
//
//    boolean isValidRange(int valToCheck, RangeType rangeType) {
//        if (rangeType == RangeType.MIN) {
//            return isInclusive ? valToCheck >= val : valToCheck > val;
//        }
//
//        return isInclusive ? valToCheck <= val : valToCheck < val;
//    }
//}
//
//class TreeNodeQueueElement {
//    TreeNode node;
//    RangeValue[] range;
//
//    TreeNodeQueueElement(TreeNode node, RangeValue[] range) {
//        this.node = node;
//        this.range = range;
//    }
//}
//
//public class BinarySearchTreeValidation {
//
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//
//        Queue<TreeNodeQueueElement> queue = new LinkedList<>();
//        queue.add(new TreeNodeQueueElement(root, new RangeValue[]{
//                new RangeValue(Integer.MIN_VALUE, true),
//                new RangeValue(Integer.MAX_VALUE, true)}));
//
//        while (!queue.isEmpty()) {
//            TreeNodeQueueElement elem = queue.peek();
//            TreeNode node = elem.node;
//            RangeValue[] range = elem.range;
//            if (!range[0].isValidRange(node.val, RangeType.MIN) || !range[1].isValidRange(node.val, RangeType.MAX)) {
//                return false;
//            }
//
//            if (elem.node.left != null) {
////                if (elem.node.val == elem.node.left.val) return false;
//                queue.add(new TreeNodeQueueElement(elem.node.left, new RangeValue[]{
//                        new RangeValue(elem.range[0].val, elem.range[0].isInclusive),
//                        new RangeValue(elem.node.val, false)}));
//            }
//
//            if (elem.node.right != null) {
////                if (elem.node.val == elem.node.right.val) return false;
//                queue.add(new TreeNodeQueueElement(elem.node.right, new RangeValue[]{
//                        new RangeValue(elem.node.val, false),
//                        new RangeValue(elem.range[1].val, elem.range[1].isInclusive)}));
//            }
//
//            queue.poll();
//        }
//
//        return true;
//    }
//
//    private TreeNode buildTree(Integer[] arr) {
//        if (arr == null || arr.length == 0) {
//            return null;
//        }
//
//        TreeNode root = new TreeNode(arr[0]);
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        int i = 1;
//        while (i < arr.length) {
//            TreeNode node = queue.peek();
//
//            if (arr[i] != null) {
//                node.left = new TreeNode(arr[i]);
//                queue.add(node.left);
//            }
//            i++;
//            if (i < arr.length) {
//                if (arr[i] != null) {
//                    node.right = new TreeNode(arr[i]);
//                    queue.add(node.right);
//                }
//                i++;
//            }
//            queue.poll();
//        }
//
//        return root;
//    }
//
//    public static void main(String[] args) {
//        BinarySearchTreeValidation binarySearchTreeValidation = new BinarySearchTreeValidation();
//        TreeNode root = binarySearchTreeValidation.buildTree(new Integer[]{3,1,5,0,2,4,6,null,null,null,3});
//        boolean isValid = binarySearchTreeValidation.isValidBST(root);
//        System.out.println(isValid);
//    }
//
//}
