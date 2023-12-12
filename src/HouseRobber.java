public class HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int numLen = nums.length;
        if (numLen == 1) {
            return nums[0];
        }

        int[] maxValues = new int[numLen+1];
        maxValues[0] = 0;
        maxValues[1] = nums[0];

        for (int i=2; i <= numLen; i++) {
            maxValues[i] = Math.max(maxValues[i-1], maxValues[i-2] + nums[i-1]);
        }

        return maxValues[numLen];
    }

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int maxValueRobbed = houseRobber.rob(new int[]{2,7,9,3,1});
        System.out.println(maxValueRobbed);
    }
}
