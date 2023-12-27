import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> existingNumbers = new HashMap<>();
        for (int num: nums) {
            existingNumbers.put(num, 0);
        }

        for (int num: nums) {
            if (existingNumbers.containsKey(num-1)) {
                existingNumbers.put(num, 1);
            }
        }

        int longestConsecutiveSeq = 0;
        for (int num: nums) {
            if (existingNumbers.get(num) == 0) {
                int j = 0, count = 0;
                while (existingNumbers.containsKey(num+j)) {
                    count++;
                    j++;
                }

                longestConsecutiveSeq = Math.max(longestConsecutiveSeq, count);
            }
        }

        return longestConsecutiveSeq;
    }
}
