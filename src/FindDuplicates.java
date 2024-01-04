import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {

    public List<Integer> findDuplicates(int[] nums) {
        int i = 0;
        int arrLen = nums.length;
        while (i < arrLen) {
            while (nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i]-1);
            }
            i++;
        }

        List<Integer> duplicates = new ArrayList<>();
        for (int j = 0; j < arrLen; j++) {
            if (j != nums[j]-1) {
                duplicates.add(nums[j]);
            }
        }

        return duplicates;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
