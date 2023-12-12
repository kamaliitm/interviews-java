public class RotatedSortedArraySearch {

    public int search(int[] nums, int target) {
        int i = 0, j = nums.length-1;

        while (i <= j) {
            if (target == nums[i]) {
                return i;
            }

            if (target == nums[j]) {
                return j;
            }

            int mid = i + (j-i)/2;

            if (target == nums[mid]) {
                return mid;
            }

            if (nums[i] < nums[mid]) {
                if (target < nums[mid] && target > nums[i]) {
                    j = mid-1;
                } else {
                    i = mid+1;
                }
            } else {
                if (target > nums[mid] && target < nums[j]) {
                    i = mid+1;
                } else {
                    j = mid-1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        RotatedSortedArraySearch rotatedSortedArraySearch = new RotatedSortedArraySearch();
//        int targetIndex = rotatedSortedArraySearch.search(new int[]{4, 5,6,7,0,1,2}, 0);
//        System.out.println(targetIndex);
//
//        int targetIndex2 = rotatedSortedArraySearch.search(new int[]{4, 5,6,7,0,1,2}, 3);
//        System.out.println(targetIndex2);

        int targetIndex3 = rotatedSortedArraySearch.search(new int[]{5,1,2,3,4}, 1);
        System.out.println(targetIndex3);
    }
}
