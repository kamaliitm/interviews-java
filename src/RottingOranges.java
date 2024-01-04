import java.util.LinkedList;
import java.util.Queue;

class RotGridElem {
    int i;
    int j;
    int timeCount;

    RotGridElem(int i, int j, int timeCount) {
        this.i = i;
        this.j = j;
        this.timeCount = timeCount;
    }

}

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[][] kShift = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int timeCount = 0;
        Queue<RotGridElem> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new RotGridElem(i, j, timeCount));
                }
            }
        }

        while (!queue.isEmpty()) {
            RotGridElem node = queue.peek();
            if (timeCount != node.timeCount) {
                timeCount = node.timeCount;
            }

            for (int s = 0; s < 4; s++) {
                int nextI = node.i + kShift[s][0];
                int nextJ = node.j + kShift[s][1];

                if (nextI < 0 || nextI >= grid.length || nextJ < 0 || nextJ >= grid[0].length
                        || grid[nextI][nextJ] == 0
                        || grid[nextI][nextJ] == 2) {
                    continue;
                }

                grid[nextI][nextJ] = 2;
                queue.add(new RotGridElem(nextI, nextJ, node.timeCount + 1));
            }

            queue.poll();
        }



        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return timeCount;
    }
}
