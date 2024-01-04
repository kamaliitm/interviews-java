import java.util.*;

public class HouseRobber2 {

    public int rob(TreeNode root) {
        if (root == null) return 0;

        Map<TreeNode, Integer> valueMap = new HashMap<>();
        return robHelper(root, valueMap);
    }

    private int robHelper(TreeNode root, Map<TreeNode, Integer> valueMap) {
        if (root == null) return 0;

        if (valueMap.containsKey(root)) {
            return valueMap.get(root);
        }

        // with root included
        int withRootVal = root.val;
        if (root.left != null) {
            withRootVal += robHelper(root.left.left, valueMap) + robHelper(root.left.right, valueMap);
        }
        if (root.right != null) {
            withRootVal += robHelper(root.right.left, valueMap) + robHelper(root.right.right, valueMap);
        }

        // without root included
        int withoutRootVal = robHelper(root.left, valueMap) + robHelper(root.right, valueMap);

        int val = Math.max(withRootVal, withoutRootVal);
        valueMap.put(root, val);
        return val;
    }

}
