public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] pathTable = new int[m][n];
        pathTable[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int fromUp = i > 0 ? pathTable[i-1][j] : 0;
                int fromLeft = j > 0 ? pathTable[i][j-1] : 0;
                pathTable[i][j] = fromLeft + fromUp;
            }
        }

        return pathTable[m-1][n-1];
    }
}
