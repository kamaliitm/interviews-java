import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {

    static BigInteger V = new BigInteger("31");

    public static int sherlockAndAnagrams(String s) {
        // Write your code here



        Map<BigInteger, Integer> substringsCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j < s.length() + 1; j++) {
                String subString = s.substring(i, j);
                BigInteger hashSubstr = hashStr(subString);
                substringsCount.put(
                        hashSubstr,
                        substringsCount.getOrDefault(hashSubstr, 0) + 1
                );
            }
        }

        int totalCount = 0;
        for (Map.Entry<BigInteger, Integer> entry: substringsCount.entrySet()) {
            totalCount += (entry.getValue() * (entry.getValue()-1))/2;
        }

        return totalCount;

    }

    static BigInteger hashStr(String s) {
        BigInteger hash = new BigInteger("0");
        for (int i = 0; i < s.length(); i++) {
            hash = hash.add(V.pow(s.charAt(i) - 'a'));
        }

        return hash;
    }

    public static void main(String[] args) {
//        System.out.println(sherlockAndAnagrams("mom"));

        int a = (4 * 3)/2;
        System.out.println(a);

    }


}
