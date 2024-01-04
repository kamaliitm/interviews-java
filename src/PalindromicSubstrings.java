public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s == null || s.isEmpty()) return 0;

        int strLen = s.length();
        Boolean[][] palindromeDp = new Boolean[strLen][strLen];
        int count = 0;
        for (int i = 0; i < strLen; i++) {
            for (int j = i; j < strLen; j++) {
                if (isPalindrome(s, i, j, palindromeDp)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int leftInd, int rightInd, Boolean[][] palindromeDp) {
        if (leftInd > rightInd) {
            return true;
        }

        if (palindromeDp[leftInd][rightInd] == null) {
            if (s.charAt(leftInd) == s.charAt(rightInd)) {
                palindromeDp[leftInd][rightInd] = isPalindrome(s, leftInd+1, rightInd-1, palindromeDp);
            } else {
                palindromeDp[leftInd][rightInd] = false;
            }
        }

        return palindromeDp[leftInd][rightInd];
    }
}
