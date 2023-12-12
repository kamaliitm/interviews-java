import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NoRepeatSubstring {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> seenSoFar = new HashMap();
        int lengthOfLongestSubstring = 0;
        int startIndex = 0;
        int endIndex = -1;

        for (int i = 0; i < s.length(); i++) {
            Character currChar = s.charAt(i);
            if (seenSoFar.containsKey(currChar)) {
                lengthOfLongestSubstring = Math.max(lengthOfLongestSubstring, endIndex-startIndex + 1);
                if (startIndex <= seenSoFar.get(currChar)) {
                    startIndex = seenSoFar.get(currChar) + 1;
                }
            }

            endIndex = i;
            seenSoFar.put(currChar, i);
        }

        lengthOfLongestSubstring = Math.max(lengthOfLongestSubstring, endIndex-startIndex + 1);

        return lengthOfLongestSubstring;
    }

    public static void main(String[] args) {
        int len = lengthOfLongestSubstring("abba");
        System.out.println(len);
    }
}
