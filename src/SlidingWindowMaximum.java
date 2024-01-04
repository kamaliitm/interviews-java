import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int numsLen = nums.length;
        int[] maximums = new int[numsLen-k+1];

        Deque<Integer> decreasingQueue = new LinkedList<>();
        for (int i = 0; i < numsLen; i++) {

            if (i >= k && nums[i-k] == decreasingQueue.peekFirst()) { // sliding window starts
                decreasingQueue.pollFirst();
            }

            while (!decreasingQueue.isEmpty() && decreasingQueue.peekLast() < nums[i]) {
                decreasingQueue.pollLast();
            }

            decreasingQueue.add(nums[i]);

            if (i >= k-1) {
                maximums[i - k + 1] = decreasingQueue.peekFirst();
            }
        }

        return maximums;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] window = slidingWindowMaximum.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(window);
    }
}
