import java.util.PriorityQueue;

public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num: nums) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else {
                if (minHeap.peek() < num) {
                    minHeap.poll();
                    minHeap.add(num);
                }
            }
        }

        return minHeap.peek();
    }
}
