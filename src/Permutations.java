import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        List<List<Integer>> permutations = new ArrayList<>();
        permuteHelper(nums, 0, permutations);

        return permutations;
    }

    private void permuteHelper(int[] nums, int index, List<List<Integer>> permutations) {
        if (index >= nums.length-1) {
            permutations.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }

        for (int j = index; j < nums.length; j++) {
            swap(nums, index, j);
            permuteHelper(nums, j+1, permutations);
            swap(nums, index, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Permutations permutationsObj = new Permutations();
        List<List<Integer>> permutations = permutationsObj.permute(new int[]{1, 2, 3});
        System.out.println(permutations);
    }
}
