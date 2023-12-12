public class FirstAndLastPosition {

    public int[] searchRange(int[] nums, int target) {

        int[] range = new int[]{-1, -1};

        if (nums == null || nums.length == 0) {
            return range;
        }

        int left = 0, right = nums.length-1;

        updateRange(nums, target, left, right, range);

        return range;
    }

    private void updateRange(int[] nums, int target, int left, int right, int[] range) {
        if (left > right || left < 0 || left >= nums.length || right < 0 || right >= nums.length) {
            return;
        }

//        if (nums[left] == target && range[0] != -1 && range[0] > left) {
//            range[0] = left;
//        }

        int mid = left + (right-left)/2;
        if (nums[mid] < target) {
            updateRange(nums, target, mid+1, right, range);
        } else if (nums[mid] > target) {
            updateRange(nums, target, left, mid-1, range);
        } else {
            if (range[0] == -1 || range[0] > mid) {
                range[0] = mid;
            }

            if (range[1] == -1 || range[1] < mid) {
                range[1] = mid;
            }

            updateRange(nums, target, mid+1, right, range);
            updateRange(nums, target, left, mid-1, range);
        }

    }

    public static void main(String[] args) {
        FirstAndLastPosition firstAndLastPosition = new FirstAndLastPosition();
        int[] range = firstAndLastPosition.searchRange(new int[]{}, 6);
        System.out.println(range);
    }
}
