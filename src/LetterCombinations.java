import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    private Map<Character, String> digitStringMap;

    public LetterCombinations() {
        digitStringMap = new HashMap<Character, String>();
        digitStringMap.put('2', "abc");
        digitStringMap.put('3', "def");
        digitStringMap.put('4', "ghi");
        digitStringMap.put('5', "jkl");
        digitStringMap.put('6', "mno");
        digitStringMap.put('7', "pqrs");
        digitStringMap.put('8', "tuv");
        digitStringMap.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();

        if (digits == null || digits.isEmpty()) {
            return combinations;
        }

        combinationsHelper(digits, 0, "", combinations);

        return combinations;
    }

    private void combinationsHelper(String digits, int currIndex, String currString, List<String> combinations) {
        if (currIndex >= digits.length()) {
            combinations.add(currString);
            return;
        }

        Character currDigit = digits.charAt(currIndex);
        String digitString = digitStringMap.get(currDigit);
        for (int i = 0; i < digitString.length(); i++) {
            currString += digitString.charAt(i);
            combinationsHelper(digits, currIndex+1, currString, combinations);
            currString = currString.substring(0, currString.length()-1);
        }

    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> combinations = letterCombinations.letterCombinations("2");
        System.out.println(combinations);
    }

}
