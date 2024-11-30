class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int r = 0;
        ListNode head = new ListNode();
        ListNode tail = head;
        while (l1 != null || l2 != null) {
            ListNode temp = null;
            if (l1 != null) {
                temp = l1;
                l1 = l1.next;
            } 
            if (l2 != null) {
                if (temp == null) 
                    temp = l2;
                else
                    temp.val += l2.val;
                l2 = l2.next;
            }
            temp.val += r;
            r = temp.val / 10;
            temp.val %= 10;
            tail.next = temp;
            tail = tail.next;
        }
        if (r != 0)
            tail.next = new ListNode(r);
        return head.next;
    }
}