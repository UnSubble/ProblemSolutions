class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode mergeNodes(ListNode head) {
        ListNode root = new ListNode(0);
        ListNode ans = root;
        int sum = 0;
        while (head.next != null) {
            if (head.val != 0)
                sum += head.val;
            else {
                root.next = head;
                head.val = sum;
                root = root.next;
                sum = 0;
            }
            head = head.next;
        }
        if (sum != 0) {
            root.next = head;
            head.val = sum;
        }
        root.next.next = null;
        return ans.next.next;
    }

}