public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int i = -1, j = -1, k = nums.length;
        int iter = 0;
        while (iter < k) {
            if (nums[iter] == 0) {
                swap(nums, i+1, iter);
                i++;
                j++;
                iter++;
            } else if (nums[iter] == 1) {
                j++;
                iter++;
            } else {
                swap(nums, iter, k-1);
                k--;
            }
        }

        System.out.println(nums);

    }

    private void swap(int[] nums, int i, int j) {
        if (isNotValidRange(nums, i, j)) return;

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private boolean isNotValidRange(int[] nums, int i, int j) {
        return i < 0 || i >= nums.length || j < 0 || j > nums.length;
    }

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors.sortColors(nums);
        System.out.println(nums);
    }
}
