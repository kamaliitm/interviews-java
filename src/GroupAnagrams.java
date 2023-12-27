import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (String str: strs) {
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String sortedStr = new String(strArr);
            List<String> anagrams = anagramsMap.getOrDefault(sortedStr, new ArrayList<>());
            anagrams.add(str);
            anagramsMap.put(sortedStr, anagrams);
        }

        return new ArrayList<>(anagramsMap.values());

    }
}
