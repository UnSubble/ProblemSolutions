import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < times.length; i++) {
            queue.offer(i);
        }
        PriorityQueue<int[]> orderSit = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        PriorityQueue<int[]> orderStand = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        
        for (int i = 0; i < times.length; i++) {
            orderStand.offer(new int[] {i, times[i][1]});
        }
        for (int i = 0; i < times.length; i++) {
            orderSit.offer(new int[] {i, times[i][0]});
        }

        int[] arr = new int[times.length];
        Arrays.fill(arr, -1);
        int[] curr = null;
        while (!orderSit.isEmpty()) {
            if (curr == null || orderSit.peek()[1] < orderStand.peek()[1])
                curr = orderSit.poll();
            else
                curr = orderStand.poll();
            if (arr[curr[0]] == -1){
                int c = queue.poll();
                arr[curr[0]] = c;
            } else {
                queue.offer(arr[curr[0]]);
                arr[curr[0]] = -1;
            }
            if (arr[curr[0]] > -1 && curr[0] == targetFriend)
                return arr[curr[0]];
            
        }
        return -1;
    }
}