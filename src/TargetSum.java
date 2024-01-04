import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
//        int arrLen = nums.length;
        int totalSum = Arrays.stream(nums).sum();
        int targetSum = totalSum + target;

        return targetSum % 2 == 1 ? 0 : getNumWaysSubsetSum(nums, targetSum/2);

    }

//    private int getNumWaysSubsetSum(int[] nums, int targetSum) {
//        int arrLen = nums.length;
//        int[][] numWaysDp = new int[arrLen+1][targetSum+1];
//        for (int i = 0; i <= arrLen; i++) {
//            numWaysDp[i][0] = 1;
//        }
//
//        for (int j = 1; j <= targetSum; j++) {
//            numWaysDp[0][j] = 0;
//        }
//
//        for (int i = 1; i <= arrLen; i++) {
//            for (int j = nums[i-1]; j <= targetSum; j++) {
//                numWaysDp[i][j] = numWaysDp[i-1][j] + numWaysDp[i-1][j-nums[i-1]];
//            }
//        }
//
//        return numWaysDp[arrLen][targetSum];
//    }

    private int getNumWaysSubsetSum(int[] nums, int targetSum) {
        int arrLen = nums.length;
        int[][] numWaysDp = new int[arrLen+1][targetSum+1];
        for (int i = 0; i <= arrLen; i++) {
            numWaysDp[i][0] = 1;
        }

        for (int j = 1; j <= targetSum; j++) {
            numWaysDp[0][j] = 0;
        }

        for (int i = 1; i <= arrLen; i++) {
            for (int j = 0; j <= targetSum; j++) {
                numWaysDp[i][j] = numWaysDp[i-1][j];
                if (j >= nums[i-1]) {
                    numWaysDp[i][j] += numWaysDp[i-1][j-nums[i-1]];
                }
            }
        }

        return numWaysDp[arrLen][targetSum];
    }

    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        System.out.println(targetSum.findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1));
    }
}
