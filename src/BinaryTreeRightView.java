import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class RightViewQueueNode {
    TreeNode node;
    int level;

    RightViewQueueNode(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }
}

public class BinaryTreeRightView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<RightViewQueueNode> queue = new LinkedList<>();
        queue.add(new RightViewQueueNode(root, 0));
        int level = -1;

        while (!queue.isEmpty()) {
            RightViewQueueNode node = queue.peek();
            if (level != node.level) {
                level = node.level;
                result.add(node.node.val);
            }

            if (node.node.right != null) queue.add(new RightViewQueueNode(node.node.right, level+1));
            if (node.node.left != null) queue.add(new RightViewQueueNode(node.node.left, level+1));

            queue.poll();
        }

        return result;
    }

    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> rightSideNodes = new ArrayList<>();

        rightSideViewHelper(root, rightSideNodes);
        return rightSideNodes;
    }

    private void rightSideViewHelper(TreeNode root, List<Integer> listSoFar) {
        if (root == null) return;

        listSoFar.add(root.val);
        if (root.right != null) {
            rightSideViewHelper(root.right, listSoFar);
        } else {
            rightSideViewHelper(root.left, listSoFar);
        }
    }
}
