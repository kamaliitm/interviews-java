import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<>();

        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            pMap.merge(p.charAt(i), 1, Integer::sum);
            sMap.merge(s.charAt(i), 1, Integer::sum);
        }

        List<Integer> startIndices = new ArrayList<>();
        if (pMap.equals(sMap)) {
            startIndices.add(0);
        }

        int pLen = p.length();

        for (int i = pLen; i < s.length(); i++) {
            // slide the window
            char leftCharAtWindow = s.charAt(i-pLen);
            int leftCharCount = sMap.get(leftCharAtWindow);
            if (leftCharCount == 1) {
                sMap.remove(leftCharAtWindow);
            } else {
                sMap.put(leftCharAtWindow, leftCharCount-1);
            }

            sMap.merge(s.charAt(i), 1, Integer::sum);

            if (pMap.equals(sMap)) {
                startIndices.add(i-pLen+1);
            }
        }

        return startIndices;
    }

    public List<Integer> findAnagramsOld(String s, String p) {

        Map<Character, Integer> pMap = new HashMap<>();
        for (char ch : p.toCharArray()) {
            int count = pMap.getOrDefault(ch, 0);
            pMap.put(ch, count+1);
        }

        List<Integer> startIndices = new ArrayList<>();
        int currStartIndex = -1;
        int currMatchedLength = 0;
        int pLen = p.length();
        Map<Character, Integer> sMap = new HashMap<>();

        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && !pMap.containsKey(s.charAt(i))) {
                currMatchedLength = 0;
                sMap = new HashMap<>();
                i++;
            }

            if (i >= s.length()) break;

            char ch = s.charAt(i);
            int pChCount = pMap.get(ch);
            int sChCount = sMap.getOrDefault(ch, 0);
            if (sChCount == pChCount) {
                // already seen all the repetitions of this char;
                // slide accordingly
                char currStartCh;
                while ((currStartCh = s.charAt(currStartIndex++)) != ch) {
                    sMap.put(currStartCh, sMap.get(currStartCh) - 1);
                }

                currMatchedLength = i - currStartIndex;
                currStartIndex++;

            } else {
                sMap.put(ch, sChCount+1);
                if (currMatchedLength == 0) {
                    currStartIndex = i;
                }
                currMatchedLength++;

                if (currMatchedLength == pLen) {
                    // we have a match
                    startIndices.add(currStartIndex);
                    // slide the window - don't reset (yet)
                    char currStartCh = s.charAt(currStartIndex);
                    sMap.put(currStartCh, sMap.get(currStartCh)-1);
                    currStartIndex++;
                    currMatchedLength--;
                }
            }

            i++;
        }

        return startIndices;
    }

    public static void main(String[] args) {
        int[] s = new int[]{1, 2, 3, 0, 5};
        int[] p = new int[]{1, 2, 3, 0, 5};
        System.out.println(s==p);
    }
}
