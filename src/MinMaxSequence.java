import java.util.*;


public class MinMaxSequence {

    public static List<Integer> solve(List<Integer> arr, List<Integer> queries) {
        List<Integer> minOfMaximums = new ArrayList<>();

        int arrSize = arr.size();
        for (int subArrSize: queries) {
            int maxOfSubArray = Collections.max(arr.subList(0, subArrSize));
            int minOfSubArrays = maxOfSubArray;

            for (int i = subArrSize; i < arrSize; i++) {
                if (arr.get(i) > maxOfSubArray) {
                    maxOfSubArray = arr.get(i);
                } else if (arr.get(i-subArrSize) == maxOfSubArray) {
                    maxOfSubArray = Collections.max(arr.subList(i-subArrSize+1, i+1));
                }

                minOfSubArrays = Math.min(minOfSubArrays, maxOfSubArray);
            }

            minOfMaximums.add(minOfSubArrays);

        }

        return minOfMaximums;
    }

    public static void main(String[] args) {
        List<Integer> minOfMaximums = solve(Arrays.asList(2, 3, 4, 5, 6), Arrays.asList(2, 3));

        System.out.println(minOfMaximums);
    }

}
