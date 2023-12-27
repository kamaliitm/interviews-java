public class MoveZeroes {

    public void moveZeroes(int[] nums) {

        int i=0, j = 0;

        while (j < nums.length) {
            while (j < nums.length && nums[j] == 0) {
                j++;
            }
            swap(nums, i, j);
            i++;
            j++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i < 0 || i >= nums.length || j < 0 || j >= nums.length) return;

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
