import java.util.HashSet;
import java.util.Set;

public class HighestValuePalindrome {

    public static String highestValuePalindrome(String s, int n, int k) {
        StringBuilder sb = new StringBuilder(s);
        Set<Integer> modifiedIndices = new HashSet<>();
        int mid = n%2 == 0 ? n/2 : n/2+1;

        for (int i = 0; i < mid; i++) {
            char left = sb.charAt(i);
            char right = sb.charAt(n-i-1);
            if (left != right) {
                if (k > 0) {
                    if (left > right) {
                        sb.setCharAt(n-i-1, left);
                    } else {
                        sb.setCharAt(i, right);
                    }
                    modifiedIndices.add(i);
                    k--;
                } else {
                    return "-1";
                }
            }
        }

        int j = 0;
        while (j < mid && k > 0) {
            if (sb.charAt(j) == '9') {
                j++;
                continue;
            }

            if (modifiedIndices.contains(j) || j == n-j-1) {
                sb.setCharAt(j, '9');
                sb.setCharAt(n-j-1, '9');
                k--;
            } else {
                if (k>1) {
                    sb.setCharAt(j, '9');
                    sb.setCharAt(n-j-1, '9');
                    k -= 2;
                }
            }

            j++;
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String highestValuePalindrome = highestValuePalindrome("12921", 5, 1);
        System.out.println(highestValuePalindrome);
    }
}
