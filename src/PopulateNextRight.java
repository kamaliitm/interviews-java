import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class NextRightNode {
    Node node;
    int level;

    NextRightNode(Node node, int level) {
        this.node = node;
        this.level = level;
    }
}

public class PopulateNextRight {

    public Node connect(Node root) {
        if (root == null || (root.left == null && root.right == null))
            return root;

        Node prev = null;
        Queue<NextRightNode> queue = new LinkedList<>();
        queue.add(new NextRightNode(root, 0));
        int level = -1;

        while (!queue.isEmpty()) {
            NextRightNode qElem = queue.peek();
            Node node = qElem.node;
            if (qElem.level != level) {
                level = qElem.level;
                node.next = null;
            } else {
                node.next = prev;
            }

            if (node.right != null) {
                queue.add(new NextRightNode(node.right, level+1));
            }

            if (node.left != null) {
                queue.add(new NextRightNode(node.left, level+1));
            }

            prev = node;

            queue.poll();
        }

        return root;
    }
}
