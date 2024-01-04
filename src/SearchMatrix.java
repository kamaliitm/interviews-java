public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int top = 0;
        int bottom = matrix.length-1;
        int rowToCheck = -1;

        while (top < bottom) {
            int mid = top + (bottom-top)/2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                top = mid+1;
                rowToCheck = mid;
            } else {
                bottom = mid-1;
                rowToCheck = mid-1;
            }
        }

        if (rowToCheck < 0 || rowToCheck > matrix.length-1) {
            return false;
        }

        int left = 0, right = matrix[rowToCheck].length-1;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (matrix[rowToCheck][mid] == target) {
                return true;
            } else if (matrix[rowToCheck][mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return false;
    }
}
