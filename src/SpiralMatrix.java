import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int dir = 0;
        int[][] kShift = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;
        int INVALID = -200;

        for (int i = 0; i < rowSize * colSize; i++) {
            result.add(matrix[x][y]);
            matrix[x][y] = INVALID;
            int nextX = x + kShift[dir][0];
            int nextY = y + kShift[dir][1];
            if (nextX < 0 || nextX >= rowSize || nextY < 0 || nextY >= colSize || matrix[nextX][nextY] == INVALID) {
                dir = (dir+1) % 4;
                nextX = x + kShift[dir][0];
                nextY = y + kShift[dir][1];
            }
            x = nextX;
            y = nextY;
        }

        return result;
    }
}
