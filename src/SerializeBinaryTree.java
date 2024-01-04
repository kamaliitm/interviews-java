import java.util.LinkedList;
import java.util.Queue;

public class SerializeBinaryTree {

    String NULL_STRING = "";
    int INVALID_VAL = -2000;
    static String DELIMITER = ";";

    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder serialized = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.peek();

            String valStr = NULL_STRING;
            if (node.val != INVALID_VAL) {
                valStr += node.val;
                queue.add(node.left == null ? new TreeNode(INVALID_VAL) : node.left);
                queue.add(node.right == null ? new TreeNode(INVALID_VAL) : node.right);
            }

            serialized.append(valStr).append(DELIMITER);

            queue.poll();
        }

        serialized.deleteCharAt(serialized.length()-1);

        return serialized.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isBlank()) return null;

        String[] nodeArr = data.split(DELIMITER);
        TreeNode root = new TreeNode(Integer.parseInt(nodeArr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        int arrLen = nodeArr.length;
        while (i < arrLen) {
            TreeNode node = queue.peek();

            TreeNode left = null;
            if (!nodeArr[i].isBlank()) {
                left = new TreeNode(Integer.parseInt(nodeArr[i]));
                queue.add(left);
            }
            node.left = left;

            i++;

            if (i < arrLen) {
                TreeNode right = null;
                if (!nodeArr[i].isBlank()) {
                    right = new TreeNode(Integer.parseInt(nodeArr[i]));
                    queue.add(right);
                }
                node.right = right;
                i++;
            }

            queue.poll();
        }

        return root;

    }

    public static void main(String[] args) {
        String[] arr = "1;2;3;;;4;5".split(DELIMITER);
//        System.out.println(arr);
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].isBlank()) {
                System.out.println(Integer.parseInt(arr[i]));
            }
        }
    }
}
