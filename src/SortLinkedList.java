public class SortLinkedList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = slow.next;
        slow.next = null;

        ListNode leftSortedList = sortList(head);
        ListNode rightSortedList = sortList(temp);

        return mergeSortedLists(leftSortedList, rightSortedList);
    }

    private ListNode mergeSortedLists(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(0);
        ListNode merged = dummyHead;

        while (left != null && right != null) {
            if (left.val < right.val) {
                merged.next = left;
                left = left.next;
            } else {
                merged.next = right;
                right = right.next;
            }

            merged = merged.next;
        }

        if (left != null) {
            merged.next = left;
        }

        if (right != null) {
            merged.next = right;
        }

        return dummyHead.next;
    }




}
