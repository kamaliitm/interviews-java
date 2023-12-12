import java.util.HashMap;
import java.util.Map;

public class MinimumWindowString {

    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> tMap = new HashMap();
        for (Character ch: t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        int tLen = t.length();
        int sLenMatched = 0;
        int start = -1, end = 0;
        int minStart = -1;
        int minLen = Integer.MAX_VALUE;
        Map<Character, Integer> sMap = new HashMap<>();
        while (end < s.length()) {
            Character sChar = s.charAt(end);
            if (tMap.containsKey(sChar)) {
                if (start == -1) start = end;

                int sCharCount = sMap.getOrDefault(sChar, 0) + 1;
                sMap.put(sChar, sCharCount);
                if (sCharCount <= tMap.get(sChar)) {
                    sLenMatched++;
                }
            }

            while (sLenMatched == tLen) {
                while (!sMap.containsKey(s.charAt(start))) {
                    start++;
                }

                // found the substring
                if (minLen > end-start+1) {
                    minStart = start;
                    minLen = end-start+1;
                }

                char chAtWindowStart = s.charAt(start);
                int chCount = sMap.get(chAtWindowStart);
                sMap.put(chAtWindowStart, --chCount);
                start++; // slide the window
                if (chCount < tMap.get(chAtWindowStart)) {
                    sLenMatched--;
                }
            }

            end++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowString minimumWindowString = new MinimumWindowString();
        String subString = minimumWindowString.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(subString);
    }
}
