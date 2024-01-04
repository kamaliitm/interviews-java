public class FindMinRotatedSortedArray {

    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;

        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] < nums[right]) {
                min = Math.min(min, nums[mid]);
                right = mid-1;
            } else {
                min = Math.min(min, nums[right]);
                left = mid+1;
                right = right-1;
            }
        }

        return min;
    }
}
