import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0) {
            return null;
        }

        List<List<Integer>> combinations = new ArrayList<>();

        combination(candidates, 0, new ArrayList<>(), 0, target, combinations);

        return combinations;
    }

    private void combination(int[] candidates, int currIndex, List<Integer> currCombination, int currSum,
                             int target, List<List<Integer>> combinations) {

        if (currSum > target) return;

        if (currSum == target) {
            combinations.add(new ArrayList<>(currCombination));
            return;
        }

        for (int i = currIndex; i < candidates.length; i++) {
            currCombination.add(candidates[i]);
            currSum += candidates[i];
            combination(candidates, i, currCombination, currSum, target, combinations);
            currCombination.remove(currCombination.size()-1);
            currSum -= candidates[i];
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> comb = combinationSum.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(comb);
    }
}
