import java.util.Scanner;

public class ReverseLinkedList {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode beforeStart = null;
        ListNode start = head;
        int i = left;
        while (i-- > 1) {
            beforeStart = start;
            start = start.next;
        }

        ListNode prev = null;
        ListNode curr = start;
        int j = right-left+1;
        while (curr != null && j-- > 0) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        start.next = curr;

        if (beforeStart != null) {
            beforeStart.next = prev;
            return head;
        }

        return prev;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
