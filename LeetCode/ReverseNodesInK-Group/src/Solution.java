
class ListNode {
	int val;
 	ListNode next;
 	ListNode() {}
 	ListNode(int val) { this.val = val; }
 	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    private ListNode head;
    private ListNode b;

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null)
            return head;
        ListNode root = new ListNode();
        this.head = head;
        b = root;

        while (check(k)) {
            insert(b, k, k);
        }
        b.next = this.head;
        return root.next;
    }

    private boolean check(int k) {
        ListNode head = this.head;
        int c = 1;
        while (head != null && head.next != null && c < k) {
            c++;
            head = head.next;
        }
        return c == k;
    }

    private void insert(ListNode first, int k, int n) {
        if (n == 0)
            return;
        ListNode next = head.next;
        ListNode temp = first.next;
        first.next = head;
        first.next.next = temp;
        head = next;
        if (n == k) {
            b = b.next;
        }
        insert(first, k, n - 1);
    }
}