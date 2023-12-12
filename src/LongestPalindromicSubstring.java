public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        int strLen = s.length();

        if (s == null || strLen == 0 || strLen == 1) {
            return s;
        }

        int startIndex = 0;
        int endIndex = 0;
        int maxLen = 1;
        boolean[][] isPalindromeTable = new boolean[strLen][strLen];

        for (int i=0; i < strLen; i++) {
            isPalindromeTable[i][i] = true;
            for (int j=0; j < i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i-j <= 2 || isPalindromeTable[j+1][i-1])) {
                    isPalindromeTable[j][i] = true;
                    if (i-j+1 > maxLen) {
                        maxLen = i-j+1;
                        startIndex = j;
                        endIndex = i;
                    }
                }
            }
        }

        return s.substring(startIndex, endIndex+1);
    }
}
