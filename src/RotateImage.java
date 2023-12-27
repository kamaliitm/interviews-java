public class RotateImage {

    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i <= (size-1)/2; i++) {
            rotateHelper(matrix, i, size);
        }
    }

    private void rotateHelper(int[][] matrix, int currLoop, int size) {

        int[] tempCells = new int[size - currLoop - 1];
        for (int i = currLoop; i <= size - currLoop - 2; i++) {
            tempCells[i-currLoop] = matrix[currLoop][i];
        }

        for (int i = currLoop; i < size - currLoop - 1; i++) {
            int temp = tempCells[i-currLoop];
            tempCells[i-currLoop] = matrix[i][size - currLoop - 1];
            matrix[i][size - currLoop - 1] = temp;
        }

        for (int i = size - currLoop - 1; i > currLoop; i--) {
            int temp = tempCells[size-i-currLoop-1];
            tempCells[size-i-currLoop-1] = matrix[size - currLoop - 1][i];
            matrix[size - currLoop - 1][i] = temp;
        }

        for (int i = size - currLoop - 1; i > currLoop; i--) {
            int temp = tempCells[size-i-currLoop-1];
            tempCells[size-i-currLoop-1] = matrix[i][currLoop];
            matrix[i][currLoop] = temp;
        }

        for (int i = currLoop; i < size - currLoop - 1; i++) {
            matrix[currLoop][i] = tempCells[i-currLoop];
        }


    }
}
