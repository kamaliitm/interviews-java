public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {

        int arrLen = nums.length;
        int i = 0;
        while (i < arrLen) {
            if (nums[i] > 0 && nums[i] <= arrLen && nums[i] != i+1) {
                swap(nums, i, nums[i]-1);
                continue;
            }
            i++;
        }

        for (int j = 0; j <= arrLen-1; j++) {
            if (nums[j] != j+1) {
                return j+1;
            }
        }

        return arrLen+1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
