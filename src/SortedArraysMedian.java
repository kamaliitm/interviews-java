public class SortedArraysMedian {

    public double findMedianOfSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null ) {
            return 0;
        }

        int num1Len = nums1.length;
        int num2Len = nums2.length;

        int[] combinedSortedArray = new int[num1Len+num2Len];
        int k = 0;
        int i = 0, j = 0;
        while(i < num1Len || j < num2Len) {
            if (i < num1Len && j < num2Len) {
                if (nums1[i] <= nums2[j]) {
                    combinedSortedArray[k] = nums1[i++];
                } else {
                    combinedSortedArray[k] = nums2[j++];
                }
            } else if (i < num1Len) {
                combinedSortedArray[k] = nums1[i++];
            } else {
                combinedSortedArray[k] = nums2[j++];
            }

            if (k == (num1Len + num2Len)/2) {
                if ((num1Len + num2Len) % 2 == 1) {
                    return combinedSortedArray[k];
                } else {
                    return ((double) (combinedSortedArray[k] + combinedSortedArray[k-1]))/2;
                }
            }
            k++;
        }

        return 0;
    }

    public static void main(String[] args) {
        SortedArraysMedian sortedArraysMedian = new SortedArraysMedian();
        double median = sortedArraysMedian.findMedianOfSortedArrays(new int[]{1, 3}, new int[]{2});
        System.out.println(median);

        double median2 = sortedArraysMedian.findMedianOfSortedArrays(new int[]{1, 3}, new int[]{2, 4});
        System.out.println(median2);
    }
}
