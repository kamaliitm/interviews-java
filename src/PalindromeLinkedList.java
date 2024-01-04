public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode mid = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }

        ListNode reversedPart = reverseLinkedList(mid.next);
        while (reversedPart != null) {
            if (reversedPart.val != head.val) {
                return false;
            }
            reversedPart = reversedPart.next;
            head = head.next;
        }

        return true;
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    public static void main(String[] args) {
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        palindromeLinkedList.isPalindrome(constructLLFromArr(new int[]{1, 1, 2, 1}));
    }

    private static ListNode constructLLFromArr(int[] arr) {
        ListNode prev = null;
        for (int i = arr.length-1; i >= 0; i--) {
            ListNode curr = new ListNode(arr[i]);
            curr.next = prev;
            prev = curr;
        }

        return prev;
    }
}
