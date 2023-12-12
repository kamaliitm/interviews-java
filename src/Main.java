import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

//        Set<Integer> set = new TreeSet<>();
//        set.add(7);
//        set.add(2);
//        set.add(5);
//        set.add(11);
//
//        System.out.println(set);

        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("x", 1);
        int y = testMap.get("y");
        System.out.println(y == 2);
    }
}