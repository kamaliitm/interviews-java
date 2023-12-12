import java.util.List;

public class AlmostSorted {

    private static final String YES = "yes";
    private static final String NO = "no";

    public static void almostSorted(List<Integer> arr) {

        int arrSize = arr.size();
        int l = 0;
        int r = arrSize - 1;

        while (l < r && arr.get(l) <= arr.get(l+1)) {
            l++;
        }

        if (l==r) {
            System.out.println(YES);
            return;
        }

        while (r > l && arr.get(r) >= arr.get(r-1)) {
            r--;
        }

        // border condition
        if ((l > 0 && arr.get(l-1) > arr.get(r)) || (r < arrSize-1 && arr.get(r+1) < arr.get(l))) {
            System.out.println(NO);
            return;
        }

        // check swap
        swap(arr, l, r);
        int start = l;
        if (start > 0) {
            start--;
        }
        int end = r;
        if (end < arrSize-1) {
            end++;
        }
        if (isSorted(arr, start, end)) {
            System.out.println(YES);
            System.out.printf("swap %s %s\n", l+1, r+1);
            return;
        }

        if (isReverseSorted(arr, l+1, r-1)) {
            System.out.println(YES);
            System.out.printf("reverse %s %s\n", l+1, r+1);
            return;
        }

        System.out.println(NO);

    }

    static void swap(List<Integer> arr, int left, int right) {
        if (left < 0 || right >= arr.size() || left >= right) {
            return;
        }

        int temp = arr.get(left);
        arr.set(left, arr.get(right));
        arr.set(right, temp);
    }

    static boolean isSorted(List<Integer> arr, int start, int end) { // start, end inclusive
        if (start >= end) {
            return true;
        }

        while (start < end) {
            if (arr.get(start) > arr.get(start+1)) {
                return false;
            }
            start++;
        }

        return true;
    }

    static boolean isReverseSorted(List<Integer> arr, int start, int end) { // start, end inclusive
        if (start >= end) {
            return true;
        }

        while (start < end) {
            if (arr.get(start) < arr.get(start+1)) {
                return false;
            }
            start++;
        }

        return true;
    }


}
