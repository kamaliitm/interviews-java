public class ArrayProduct {

    public int[] productExceptSelf(int[] nums) {

        int[] arrProducts = new int[nums.length];

        int leftPrefixProduct = 1;
        for (int i=0; i < nums.length; i++) {
            arrProducts[i] = leftPrefixProduct;
            leftPrefixProduct *= nums[i];
        }

        int rightPrefixProduct = 1;
        for (int i=nums.length-1; i >= 0; i--) {
            arrProducts[i] *= rightPrefixProduct;
            rightPrefixProduct *= nums[i];
        }

        return arrProducts;
    }

    public static void main(String[] args) {
        ArrayProduct arrayProduct = new ArrayProduct();
        int[] arrayProducts = arrayProduct.productExceptSelf(new int[]{-1,1,0,-3,3});
        System.out.println(arrayProducts);
    }
}
