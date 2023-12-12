import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WeightedUniformStrings {


    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
        // Write your code here

        int strLen = s.length();
        if (s.length() == 0) {
            return queries.stream().map(q -> "No").collect(Collectors.toList());
        }

        Set<Integer> weights = new HashSet<>();

        int currWeight = weightOfChar(s.charAt(0));
        weights.add(currWeight);
        for (int i = 1; i < strLen; i++) {
            int weightAtIndex = weightOfChar(s.charAt(i));
            if (s.charAt(i) != s.charAt(i-1)) {
                currWeight = weightAtIndex;
            } else {
                currWeight += weightAtIndex;
            }

            weights.add(currWeight);
        }

        List<String> responses = new ArrayList<>();
        for (Integer q: queries) {
            if (weights.contains(q)) {
                responses.add("Yes");
            } else {
                responses.add("No");
            }
        }

        return responses;
    }

    static int weightOfChar(char c) {
        return c - 'a' + 1;
    }

}
