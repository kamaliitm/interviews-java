import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class ListNodeComparator implements Comparator<ListNode> {

    @Override
    public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
    }
}

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, new ListNodeComparator());

        for (ListNode listNode: lists) {
            if (listNode != null) {
                minHeap.add(listNode);
            }
        }

        ListNode mergedListNode = new ListNode();
        ListNode currNode = mergedListNode;
        while (minHeap.size() > 0) {
            ListNode minListNode = minHeap.poll();
            currNode.next = minListNode;
            if (minListNode.next != null) {
                minHeap.add(minListNode.next);
            }
            currNode = currNode.next;
        }

        return mergedListNode.next;
    }

    public static void main(String[] args) {
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode mergedLists = mergeKSortedLists.mergeKLists(new ListNode[]{
                new ListNode(1, new ListNode(4)),
                new ListNode(2, new ListNode(3)),
                new ListNode(5, new ListNode(9)),
                new ListNode(7, new ListNode(11)),
        });
        System.out.println(mergedLists);

    }
}
