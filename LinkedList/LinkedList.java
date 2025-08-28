package LinkedList;

public class LinkedList {

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null){
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        if (head1.val <= head2.val) {
            head1.next = mergeTwoLists(head1.next, head2);
            return head1;
        }
        else {
            head2.next = mergeTwoLists(head1, head2.next);
            return head2;
        }

    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(mergeTwoLists(list1, list2));
    }
}
