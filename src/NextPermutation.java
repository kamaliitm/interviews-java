import java.util.Arrays;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int arrLen = nums.length;

        int i = arrLen-1;
        while (i > 0 && nums[i] <= nums[i-1]) {
            i--;
        }

        if (i==0) {
            reverseArr(nums, 0, arrLen-1);
            return;
        }

        int nextGreaterVal = Integer.MAX_VALUE;
        int nextGreaterIndex = -1;
        for (int j = arrLen-1; j >= i; j--) {
            if (nums[j] < nextGreaterVal && nums[j] > nums[i-1]) {
                nextGreaterVal = nums[j];
                nextGreaterIndex = j;
            }
        }

        swap(nums, i-1, nextGreaterIndex);
        reverseArr(nums, i, arrLen-1);
    }

    private void swap(int[] nums, int i, int j) {
        if (isNotValidRange(nums, i, j)) {
            return;
        }

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverseArr(int[] nums, int i, int j) {
        if (isNotValidRange(nums, i, j) || i > j) {
            return;
        }

//        int i = 0, j = nums.length-1;
        while (i<j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private boolean isNotValidRange(int[] nums, int i, int j) {
        int arrLen = nums.length;
        return i < 0 || i > arrLen-1 || j < 0 || j > arrLen-1;
    }

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = new int[]{2, 3, 1};
        nextPermutation.nextPermutation(nums);
        System.out.println(nums);
    }
}
