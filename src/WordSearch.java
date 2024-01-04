public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;

        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i=0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(0) == board[i][j] && checkDFS(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkDFS(char[][] board, int i, int j, String word, int offset,
                             boolean[][] visited) {

        if (word.length() == offset) {
            return true;
        }

        if (!isValidRange(board, i, j) || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;

        if (board[i][j] == word.charAt(offset) &&
                (
                        checkDFS(board, i, j-1, word, offset+1, visited) ||
                                checkDFS(board, i-1, j, word, offset+1, visited) ||
                                checkDFS(board, i, j+1, word, offset+1, visited) ||
                                checkDFS(board, i+1, j, word, offset+1, visited)
                )
        ) {
            return true;
        }

        visited[i][j] = false;

        return false;
    }

    private boolean isValidRange(char[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[i].length;
    }

}
