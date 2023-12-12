public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {

        int leftMaxProduct = Integer.MIN_VALUE;
        int leftPrefixProduct = 1;
        for (int num : nums) {
            leftPrefixProduct *= num;
            leftMaxProduct = Math.max(leftMaxProduct, Math.max(leftPrefixProduct, num));
            if (leftPrefixProduct == 0) {
                leftPrefixProduct = 1;
            }
        }

        int rightMaxProduct = Integer.MIN_VALUE;
        int rightPrefixProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            rightPrefixProduct *= nums[i];
            rightMaxProduct = Math.max(rightMaxProduct, Math.max(rightPrefixProduct, nums[i]));
            if (rightPrefixProduct == 0) {
                rightPrefixProduct = 1;
            }
        }

        return Math.max(leftMaxProduct, rightMaxProduct);
    }

    public static void main(String[] args) {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        int maxProduct = maximumProductSubarray.maxProduct(new int[]{-1,-2,-3,0});
        System.out.println(maxProduct);
    }
}
