import java.util.ArrayList;
import java.util.List;

public class LongestCommonChild {

    public static int commonChild(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();

        if (s1Len == 0 || s2Len == 0) {
            return 0;
        }

        int[][] longestChildData = new int[s1.length()+1][s2.length()+1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    longestChildData[i][j] = 0;
                } else {
                    if (s1.charAt(i-1) == s2.charAt(j-1)) {
                        longestChildData[i][j] = 1 + longestChildData[i-1][j-1];
                    } else {
                        longestChildData[i][j] = Math.max(longestChildData[i-1][j], longestChildData[i][j-1]);
                    }
                }
            }
        }

        return longestChildData[s1Len][s2Len];

    }

    public static void main(String[] args) {
        int longestChildLength = commonChild("SHINCHAN", "NOHARAAA");
        System.out.println(longestChildLength);
    }
}
