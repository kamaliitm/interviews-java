import java.util.ArrayList;
import java.util.List;

public class ConnectedCells {

    public static int connectedCell(List<List<Integer>> matrix) {
        if (matrix == null || matrix.size() == 0 || matrix.get(0) == null || matrix.get(0).size() == 0) {
            return 0;
        }

        int maxRegion = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) != 0) {
                    maxRegion = Math.max(maxRegion, connectedCellDFS(matrix, i, j));
                }
            }
        }
        return maxRegion;

    }

    static int connectedCellDFS(List<List<Integer>> matrix, int row, int col) {

        if (row < 0 || row >= matrix.size() || col < 0 || col >= matrix.get(row).size()
                || matrix.get(row).get(col) == 0) {
            return 0;
        }

        int regionSize = 1;
        List<Integer> matrixRow = matrix.get(row);
        matrixRow.set(col, 0);
        matrix.set(row, matrixRow);

        int[][] adjacentIndices = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        for (int i = 0; i < adjacentIndices.length; i++) {
            regionSize += connectedCellDFS(matrix, row+adjacentIndices[i][0], col+adjacentIndices[i][1]);
        }

        return regionSize;
    }

    public static void main(String[] args) {

    }
}
