
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class Board {
    private char[][] board;
    private int redX, redY, goalX, goalY;
    private int purpleX, purpleY, goalXP, goalYP;
    private boolean singleCube;
    private final List<char[][]> history = new ArrayList<>();
    public Board(int level) {
        initializeBoard(level);
        saveBoardState();
    }
    public char[][] getBoard() {
        return board;
    }
    public void setBoardCell(int x, int y, char value) {
        board[x][y] = value;
    }
    public int getRedX() {
        return redX;
    }
    public int getRedY() {
        return redY;
    }
    public int getGoalX() {
        return goalX;
    }
    public int getGoalY() {
        return goalY;
    }
    public int getPurpleX() {
        return purpleX;
    }
    public int getPurpleY() {
        return purpleY;
    }
    public void setRedX(int redX) {
        this.redX = redX;
    }
    public void setRedY(int redY) {
        this.redY = redY;
    }
    public void setPurpleX(int purpleX) {
        this.purpleX = purpleX;
    }
    public void setPurpleY(int purpleY) {
        this.purpleY = purpleY;
    }
    public int getGoalXP() {
        return goalXP;
    }
    public int getGoalYP() {
        return goalYP;
    }
    public boolean isSingleCube() {
        return singleCube;
    }
    public     void initializeBoard(int level) {
        switch (level) {
            case 1, 2, 3 -> {
                singleCube = true;
                initializeSingleCubeLevel(level);
            }
            case 4, 5 -> {
                singleCube = false;
                initializeTwoCubeLevel(level);
            }
        }
    }
    private void initializeSingleCubeLevel(int level) {
        switch (level) {
            case 1 -> {
                board = new char[][]{
                        {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
                        {'B', 'R', 'W', 'W', 'W', 'W', 'W', 'G', 'B'},
                        {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}
                };
                redX = 1;
                redY = 1;
                goalX = 1;
                goalY = 7;
            }
            case 2 -> {
                board = new char[][]{
                        {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
                        {'B', 'W', 'W', 'B', 'W', 'W', 'W', 'B'},
                        {'B', 'R', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'B', 'W', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'B', 'B', 'B', 'B', 'G', 'B', 'B', 'B'},
                        {'W', 'W', 'W', 'B', 'B', 'B', 'W', 'W'}
                };
                redX = 2;
                redY = 1;
                goalX = 4;
                goalY = 4;
            }
            case 3 -> {
                board = new char[][]{
                        {'W', 'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
                        {'W', 'W', 'B', 'R', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'B', 'B', 'B', 'W', 'W', 'B', 'B', 'B', 'B', 'B', 'W', 'B', 'B'},
                        {'B', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'B', 'W', 'B', 'B'},
                        {'B', 'W', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'W', 'W', 'B'},
                        {'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'B', 'B', 'B', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'W', 'W', 'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}
                };
                redX = 1;
                redY = 3;
                goalX = 3;
                goalY = 5;
            }
        }
    }
    private void initializeTwoCubeLevel(int level) {
        switch (level) {
            case 4 -> {
                board = new char[][]{
                        {'B', 'B', 'B', 'B', 'B', 'B', 'W', 'W', 'W'},
                        {'B', 'G', 'W', 'W', 'W', 'B', 'B', 'W', 'W'},
                        {'B', 'W', 'W', 'G', 'W', 'W', 'B', 'B', 'B'},
                        {'B', 'W', 'W', 'W', 'W', 'W', 'P', 'R', 'B'},
                        {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}
                };
                redX = 3;
                redY = 7;
                goalX = 1;
                goalY = 1;
                purpleX = 3;
                purpleY = 6;
                goalXP = 2;
                goalYP = 3;

//                board = new char[originalBoard.length][originalBoard[0].length];
//                for (int i = 0; i < originalBoard.length; i++) {
//                    System.arraycopy(originalBoard[i], 0, board[i], 0, originalBoard[i].length);
//                }
            }
            case 5 -> {
                board = new char[][]{
                        {'W', 'W', 'W', 'W', 'B', 'B', 'B', 'B', 'B'},
                        {'W', 'W', 'W', 'W', 'B', 'W', 'W', 'W', 'B'},
                        {'B', 'B', 'B', 'B', 'B', 'G', 'B', 'W', 'B'},
                        {'B', 'R', 'W', 'W', 'W', 'W', 'B', 'W', 'B'},
                        {'B', 'P', 'W', 'G', 'B', 'W', 'B', 'W', 'B'},
                        {'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
                };
                redX = 3;
                redY = 1;
                goalX = 2;
                goalY = 5;
                purpleX = 4;
                purpleY = 1;
                goalXP = 4;
                goalYP = 3;
            }
        }
    }
    public static char[][] copyBoard(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    private void saveBoardState() {
        history.add(copyBoard(board));
    }

    public void printCurrentBoard() {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
//    public void restorePreviousState() {
//        if (history.size() > 1) {
//            history.remove(history.size() - 1);
//            board = copyBoard(history.get(history.size() - 1));
//        }
//    }
    public void updateBoardAfterMove() {
        saveBoardState();
        printCurrentBoard();
    }
}
