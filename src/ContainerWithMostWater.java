public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0, j = height.length-1;

        while (i < j) {
            int minHeight = Math.min(height[i], height[j]);
            maxArea = Math.max(maxArea, minHeight * (j-i));
            if (height[i] < height[j]) {
                i++;
            } else if (height[i] > height[j]) {
                j--;
            } else {
                i++;
                j--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        int maxArea = containerWithMostWater.maxArea(new int[]{1,8,6,2,5,4,8,3,7});
        System.out.println(maxArea);
    }
}
