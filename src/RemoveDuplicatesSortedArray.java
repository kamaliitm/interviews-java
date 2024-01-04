public class RemoveDuplicatesSortedArray {
    public int removeDuplicates(int[] nums) {
        int arrLen = nums.length;
        if (arrLen == 1) return 1;

        int i = 0;
        int j = 1;
        while (i < arrLen) {
            while (j < arrLen && nums[j] == nums[j-1]) {
                j++;
            }
            i++;
            if (i < arrLen && j < arrLen) {
                nums[i] = nums[j];
            }
            j++;
        }

        return i+1;
    }


}
