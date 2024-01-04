import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        if (nums == null || nums.length == 0) {
            return null;
        }

        List<List<Integer>> powerSet = new ArrayList<>();

        for (int i = 0; i < (1 << nums.length); i++) {
            int bitArray = i;
            List<Integer> subset = new ArrayList<>();

            while (bitArray > 0) {
                int index = (int) (Math.log(bitArray & ~(bitArray-1)) / Math.log(2));
                subset.add(nums[index]);
                bitArray &= bitArray - 1;
            }


            powerSet.add(subset);
        }

        return powerSet;

    }

    public static  void main(String[] args) {
        System.out.println((int) (Math.log(10)/Math.log(2)));
    }


}