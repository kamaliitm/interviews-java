public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = swapPairs(head.next.next);
        ListNode temp = head.next;
        head.next.next = head;
        head.next = curr;
        return temp;
    }
}
