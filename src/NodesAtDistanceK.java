import java.util.*;

class DistanceKQueueElement {
    TreeNode node;
    int distance;

    DistanceKQueueElement(TreeNode node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class NodesAtDistanceK {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        Map<Integer, Boolean> visited = new HashMap<>();
        List<Integer> nodesAtDistanceK = new ArrayList<>();
        Queue<DistanceKQueueElement> queue = new LinkedList<>();
        queue.add(new DistanceKQueueElement(target, 0));

        while (!queue.isEmpty()) {
            DistanceKQueueElement elem = queue.poll();
            TreeNode node = elem.node;
            visited.put(node.val, true);

            if (elem.distance == k) {
                nodesAtDistanceK.add(node.val);
            } else {
                if (node.left != null && !visited.containsKey(node.left.val))
                    queue.add(new DistanceKQueueElement(node.left, elem.distance + 1));

                if (node.right != null && !visited.containsKey(node.right.val))
                    queue.add(new DistanceKQueueElement(node.right, elem.distance + 1));

                TreeNode parent = parentMap.get(node.val);
                if (parent != null && !visited.containsKey(parent.val))
                    queue.add(new DistanceKQueueElement(parent, elem.distance + 1));
            }

        }

        return nodesAtDistanceK;

    }

    private void buildParentMap(TreeNode node, TreeNode parent, Map<Integer, TreeNode> parentMap) {
        if (node == null) return;

        parentMap.put(node.val, parent);
        buildParentMap(node.left, node, parentMap);
        buildParentMap(node.right, node, parentMap);
    }
}
