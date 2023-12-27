public class RegularExpressionMatching {
    
    public boolean isMatch(String s, String p) {

        boolean[][] dpTable = new boolean[s.length()+1][p.length()+1];

        dpTable[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i-1) == '*') {
                dpTable[0][i] = i < 2 || dpTable[0][i - 2];
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    dpTable[i][j] = dpTable[i-1][j-1];
                } else {
                    if (p.charAt(j-1) == '*') {
                        if (s.charAt(i-1) != p.charAt(j-2) && p.charAt(j-2) != '.') {
                            dpTable[i][j] = dpTable[i][j-2];
                        } else {
                            dpTable[i][j] = dpTable[i-1][j] || dpTable[i][j-1] || dpTable[i][j-2];
                        }
                    }
                }
            }
        }

        return dpTable[s.length()][p.length()];
    }


    private boolean isMatchHelper(String s, int sIndex, String p, int pIndex) {
        if (sIndex == s.length()) return isPatternFinished(p, pIndex);
        
        if (pIndex >= p.length()) return false;

        boolean hasAsterisk = pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*';
        if (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.') {
            return isMatchHelper(s, sIndex+1, p, 
                    hasAsterisk ? pIndex : pIndex + 1);
        }
        
        if (hasAsterisk) {
            return isMatchHelper(s, sIndex, p, pIndex+2);
        }
        
        return false;
    }

    private boolean isPatternFinished(String p, int pIndex) {
        if (p.isBlank()) return true;

        int pLen = p.length();
        if (pIndex == pLen) return true;

        int i = pLen-1;
        while (i >= pIndex) {
            if (p.charAt(i) != '*') return false;

            i -= 2;
        }

        return true;
    }

    public static void main(String[] args) {
        boolean isMatch = (new RegularExpressionMatching()).isMatch("aaa", "aaaa");
        System.out.println(isMatch);
    }
}
