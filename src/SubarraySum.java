import java.util.HashMap;
import java.util.Map;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int numLen = nums.length;
        int[] prefixSums = new int[numLen];
        int prefixSum = 0;

        for (int i = 0; i < numLen; i++) {
            prefixSum += nums[i];
            prefixSums[i] = prefixSum;
        }

        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int sumCount = 0;

        for (int i=0; i < numLen; i++) {
            if (prefixSums[i] == k) {
                sumCount++;
            }

            if (prefixSumMap.containsKey(prefixSums[i]-k)) {
                sumCount += prefixSumMap.get(prefixSums[i]-k);
            }

            int prefixSumCount = prefixSumMap.getOrDefault(prefixSums[i], 0);
            prefixSumMap.put(prefixSums[i], prefixSumCount+1);
        }

        return sumCount;
    }

    public static void main(String[] args) {
        SubarraySum subarraySum = new SubarraySum();
        int sumCount = subarraySum.subarraySum(new int[]{0,0,0,0,0,0,0,0,0,0}, 0);
        System.out.println(sumCount);
    }
}
