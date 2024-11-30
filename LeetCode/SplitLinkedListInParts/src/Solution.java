class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    private int k;
    private int c;
    private ListNode last;

    public ListNode[] splitListToParts(ListNode head, int k) {
        int length = length(head);
        this.k = length / k == 0 ? 0 : length % k;
        c = length / k == 0 ? 1 : length / k;
        ListNode[] nodes = new ListNode[k];
        last = head;
        split(nodes, 0);
        return nodes;
    }

    private void split(ListNode[] nodes, int i) {
        if (last == null || nodes.length == i)
            return;
        int j = c;
        nodes[i] = last;
        while (--j > 0)
            last = last.next;
        if (k > 0) {
            k--;
            last = last.next;
        }
        ListNode temp = last.next;
        last.next = null;
        last = temp;
        split(nodes, i + 1);
    }

    private int length(ListNode node) {
        if (node == null)
            return 0;
        return length(node.next) + 1;
    }
}