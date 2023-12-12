import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        int numsLen = nums.length;
        List<List<Integer>> threeSumZeroNumbers = new ArrayList<>();
        for (int i = 0; i < numsLen; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i+1;
            int k = numsLen - 1;

            while (j < k) {
                int numAtIndexJ = nums[j];
                int numAtIndexK = nums[k];
                int sumOfRemTwoNumbers = numAtIndexJ + numAtIndexK;
                if (sumOfRemTwoNumbers == -nums[i]) {
                    threeSumZeroNumbers.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    while (j < k && nums[j] == numAtIndexJ) {
                        j++;
                    }

                    while (k > j && nums[k] == numAtIndexK) {
                        k--;
                    }
                } else if (sumOfRemTwoNumbers < -nums[i]) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return threeSumZeroNumbers;
    }

    private boolean isTripletAlreadyPresent(int[] triplet, List<List<Integer>> threeSumZeroNumbers) {
        for (List<Integer> tripletPresent: threeSumZeroNumbers) {

            if (triplet[0] == tripletPresent.get(0)
                    && triplet[1] == tripletPresent.get(1)
                    && triplet[2] == tripletPresent.get(2)) {
                return true;
            }
        }

        return false;
    }



    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> threeSumNumbers = threeSum.threeSum(new int[]{-1, -1, 0, 1, 2, -1, -4});
        System.out.println(threeSumNumbers);
    }
}
