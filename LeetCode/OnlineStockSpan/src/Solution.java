class Node {
    private static Node last;
    Node next;
    Node prev;
    int val;

    public Node(int val) {
        if (last != null) {
            prev = last;
            prev.next = this;
        }
        last = this;
        this.val = val;
    }

    public void clear() {
        last = this;
        prev = null;
        next = null;
    }

    public static Node getLast() {
        return last;
    }
}

class StockSpanner {
    private int max;
    private int size;
    Node root;

    public StockSpanner() {
        max = 0;
        size = 0;
        root = new Node(Integer.MAX_VALUE);
    }

    public int next(int price) {
        size++;
        if (max <= price) {
            max = price;
            root.clear();
            new Node(price);
            return size;
        }
        new Node(price);
        int res = 1;
        Node last = Node.getLast().prev;
        while (last.val <= price) {
            res++;
            last = last.prev;
        }
        return res;
    }
}

