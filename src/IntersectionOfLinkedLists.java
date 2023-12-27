public class IntersectionOfLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = 0;
        ListNode curr = headA;
        while (curr != null) {
            lenA++;
            curr = curr.next;
        }

        int lenB = 0;
        curr = headB;
        while (curr != null) {
            lenB++;
            curr = curr.next;
        }

        ListNode shorter = lenA < lenB ? headA : headB;
        ListNode longer = lenA < lenB ? headB : headA;
        int diff = Math.abs(lenA - lenB);
        for (int i=0; i < diff; i++) {
            longer = longer.next;
        }

        while (longer != null && longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }

        return longer;
    }
}
