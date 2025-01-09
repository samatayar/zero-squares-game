
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class Board {
    private char[][] board;
    private int redX, redY, goalX, goalY;
    private int purpleX, purpleY, goalXP, goalYP;
    private boolean singleCube;
    private final List<Board> history = new ArrayList<>();
    public Board(int level) {
        initializeBoard(level);
        saveBoardState();
    }
    private int[][] playerPositions;
    private int rows;
    private int cols;

    public int calculateMoveCost(String direction) {
        int totalCost = 0;
        for (int[] player : playerPositions) {
            int x = player[0];
            int y = player[1];
            switch (direction.toLowerCase()) {
                case "up":
                    totalCost += (x > 0) ? 1 : Integer.MAX_VALUE;
                    break;
                case "down":
                    totalCost += (x < rows - 1) ? 1 : Integer.MAX_VALUE;
                    break;
                case "left":
                    totalCost += (y > 0) ? 1 : Integer.MAX_VALUE;
                    break;
                case "right":
                    totalCost += (y < cols - 1) ? 1 : Integer.MAX_VALUE;
                    break;
            }
        }
        return totalCost;
    }

    public int[][] getPlayerPositions() {
        return playerPositions;
    }
    public char[][] getBoard() {
        return this.board;
    }
    public Board getBoardB() {
        return this;
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
    public void removeRedCube() {
        setRedPosition(-1, -1);
    }

    public void removePurpleCube() {
        setPurplePosition(-1, -1);
    }

    public void initializeBoard(int level) {
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
                        {'W', 'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W', 'W'},
                        {'W', 'B', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'B', 'W'},
                        {'B', 'B', 'W', 'W', 'B', 'W', 'G', 'B', 'W', 'W', 'B', 'B'},
                        {'B', 'W', 'W', 'B', 'W', 'G', 'W', 'W', 'B', 'W', 'W', 'B'},
                        {'B', 'W', 'W', 'W', 'W', 'B', 'W', 'W', 'B', 'B', 'W', 'B'},
                        {'B', 'R', 'B', 'W', 'B', 'B', 'W', 'W', 'W', 'B', 'P', 'B'},
                        {'B', 'W', 'W', 'W', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'B'},
                        {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}

                };
                redX = 5;
                redY = 1;
                goalX = 3;
                goalY = 5;
                purpleX = 5;
                purpleY = 10;
                goalXP = 2;
                goalYP = 6;


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


    private void setGoalY(int goalY) {
        this.goalY=goalY;
    }

    private void setGoalX(int goalX) {
        this.goalX=goalX;
    }

    private void setBoard(char[][] board) {
        this.board=board;
    }

    public static Board copyBoard(Board board) {
        Board Boardcopy = board;
        char[][] original =board.getBoard();

        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        Boardcopy.board=copy;
        return Boardcopy;
    }
    public Board cloneState() {
        Board clonedBoard = new Board(1);

        char[][] clonedArray = new char[this.board.length][];
        for (int i = 0; i < this.board.length; i++) {
            clonedArray[i] = Arrays.copyOf(this.board[i], this.board[i].length);
        }
        clonedBoard.setBoard(clonedArray);

        clonedBoard.setRedX(this.redX);
        clonedBoard.setRedY(this.redY);
        clonedBoard.setGoalX(this.goalX);
        clonedBoard.setGoalY(this.goalY);

        if (!this.singleCube) {
            clonedBoard.setPurpleX(this.purpleX);
            clonedBoard.setPurpleY(this.purpleY);
        }
        clonedBoard.goalXP = this.goalXP;
        clonedBoard.goalYP = this.goalYP;
        clonedBoard.singleCube = this.singleCube;

        return clonedBoard;
    }

    private void saveBoardState() {
        history.add(copyBoard(this));
    }

    public void printCurrentBoard() {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public void updateBoardAfterMove() {
        saveBoardState();
        printCurrentBoard();
    }

    public void setRedPosition(int redX, int newRedY) {
        this.redX=redX;
        this.redY=newRedY;
    }

    public void setPurplePosition(int newPurpleX, int purpleY) {
        this.purpleX=newPurpleX;
        this.purpleY=purpleY;
    }
    public int getMisplacedTiles() {
        int misplacedCount = 0;

        if (redX != goalX || redY != goalY) {
            misplacedCount++;
        }

        if (!singleCube && (purpleX != goalXP || purpleY != goalYP)) {
            misplacedCount++;
        }

        return misplacedCount;
    }

}
