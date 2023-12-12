import java.util.Arrays;
import java.util.List;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;

        int sLen = s.length();

        boolean[] canBreakSoFar = new boolean[sLen];
        canBreakSoFar[0] = wordDict.contains(s.substring(0, 1));

        for (int i=1; i < sLen; i++) {
            if (wordDict.contains(s.substring(0, i+1))) {
                canBreakSoFar[i] = true;
            } else {
                for (int j=i; j > 0; j--) {
                    if (canBreakSoFar[j-1] && wordDict.contains(s.substring(j, i+1))) {
                        canBreakSoFar[i] = true;
                        break;
                    }
                }
            }
        }

        return canBreakSoFar[sLen-1];

    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        boolean canBreak = wordBreak.wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat"));
        System.out.println(canBreak);
    }
}
