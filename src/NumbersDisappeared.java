import java.util.ArrayList;
import java.util.List;

public class NumbersDisappeared {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int i = 0;
        while (i < nums.length) {
            while (i != nums[i] - 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i]-1);
            }
            i++;
        }

        List<Integer> result = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            if (j != nums[j]-1) {
                result.add(j+1);
            }
        }

        return  result;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
