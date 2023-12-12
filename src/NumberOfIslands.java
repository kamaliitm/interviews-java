import java.util.LinkedList;
import java.util.Queue;

class Cell {
    int i;
    int j;

    Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int numIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '0') {
                    markIsland(grid, i, j);
                    numIslands++;
                }
            }
        }

        return numIslands;
    }

    private void markIsland(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        int[][] neighbours = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (queue.size() > 0) {
            int[] ij = queue.peek();
            grid[ij[0]][ij[1]] = '0';
            for (int k = 0; k < 4; k++) {
                int next_i = ij[0] + neighbours[k][0];
                int next_j = ij[1] + neighbours[k][1];
                if (next_i < 0 || next_i >= grid.length || next_j < 0 || next_j >= grid[i].length ||
                        grid[next_i][next_j] == '0') {
                    continue;
                }
                queue.add(new int[]{next_i, next_j});
            }

            queue.poll();
        }
    }

    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int numIslands = numberOfIslands.numIslands(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        });
        System.out.println(numIslands);

        int numIslands2 = numberOfIslands.numIslands(new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        });
        System.out.println(numIslands2);
    }
}
