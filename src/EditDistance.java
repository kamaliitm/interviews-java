public class EditDistance {

    public int minDistance(String word1, String word2) {
        if (word1.isBlank() && word2.isBlank()) return 0;

        if (word1.isBlank()) return word2.length();
        if (word2.isBlank()) return word1.length();

        int[][] editDistanceTable = new int[word1.length()+1][word2.length()+1];
        editDistanceTable[0][0] = 0;

        for (int i=1; i <= word1.length(); i++) {
            editDistanceTable[i][0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                editDistanceTable[0][j] = j;
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    editDistanceTable[i][j] = editDistanceTable[i-1][j-1];
                } else {
                    int insertDist = editDistanceTable[i][j - 1];
                    int deleteDist = editDistanceTable[i - 1][j];
                    int replaceDist = editDistanceTable[i - 1][j - 1];
                    editDistanceTable[i][j] = 1 + Math.min(insertDist, Math.min(deleteDist, replaceDist));
                }
            }
        }

        return editDistanceTable[word1.length()][word2.length()];

    }


}
