
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class State2 extends JPanel {
    public Board board;
    private  int level = 2;
    private static final int CELL_SIZE = 50; // Size of each cell


    public State2() {
        board = new Board(level);
    }
    public boolean checkGoal() {
        if (board.isSingleCube()) {
            return board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY();
        } else {
            boolean redAtGoal = board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY();
            boolean purpleAtGoal = board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP();
            return redAtGoal && purpleAtGoal;
        }
    }
    public int getHeuristicValue() {
        int redDistance = Math.abs(board.getRedX() - board.getGoalX()) + Math.abs(board.getRedY() - board.getGoalY());
        int purpleDistance = 0;

        if (!board.isSingleCube()) {
            purpleDistance = Math.abs(board.getPurpleX() - board.getGoalXP()) + Math.abs(board.getPurpleY() - board.getGoalYP());
        }

        return redDistance + purpleDistance;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        char[][] gameBoard = board.getBoard();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if ((i == board.getGoalX() && j == board.getGoalY() && gameBoard[i][j] != 'R') ||
                        (!board.isSingleCube() && i == board.getGoalXP() && j == board.getGoalYP() && gameBoard[i][j] != 'P')) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                    g.setColor(i == board.getGoalX() && j == board.getGoalY() ? Color.RED : new Color(128, 0, 128));
                    g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    g.setColor(switch (gameBoard[i][j]) {
                        case 'B' -> Color.BLACK;
                        case 'R' -> Color.RED;
                        case 'P' -> new Color(128, 0, 128);
                        default -> Color.WHITE;
                    });
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.GRAY);
                    g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }



    public Board   moveRight() {
        if (Math.abs(board.getPurpleY() - board.getRedY()) == 1) {
            if (board.getRedY() > board.getPurpleY()) {
                // Move Red Cube
                if (board.getRedX() >= 0 && board.getRedY() >= 0) {
                    int redX = board.getRedX();
                    int redY = board.getRedY();

                    int newRedY = redY;
                    while (newRedY < board.getBoard()[0].length - 1 &&
                            board.getBoard()[redX][newRedY + 1] != 'B' &&
                            board.getBoard()[redX][newRedY + 1] != 'R' &&
                            board.getBoard()[redX][newRedY + 1] != 'P') {
                        newRedY++;
                    }

                    if (newRedY != redY) {
                        // Clear the old position
                        if (redX != board.getGoalX() || redY != board.getGoalY()) {
                            if (board.getBoard()[redX][redY] != 'G') {
                                board.getBoard()[redX][redY] = 'W';
                            }
                        } else {
                            board.getBoard()[redX][redY] = 'G';
                        }

                        board.setRedPosition(redX, newRedY);
                        board.getBoard()[redX][newRedY] = 'R';
                    }
                }
            } else if (board.getPurpleY() > board.getRedY()) {
                // Move Purple Cube
                if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
                    int purpleX = board.getPurpleX();
                    int purpleY = board.getPurpleY();
//////////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    if (purpleY == board.getRedY() + 1) {
                       return board;
                    }
/////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
                    int newPurpleY = purpleY;
                    while (newPurpleY < board.getBoard()[0].length - 1 &&
                            board.getBoard()[purpleX][newPurpleY + 1] != 'B' &&
                            board.getBoard()[purpleX][newPurpleY + 1] != 'R' &&
                            board.getBoard()[purpleX][newPurpleY + 1] != 'P') {
                        newPurpleY++;
                    }

                    if (newPurpleY != purpleY) {
                        // Clear the old position
                        if (purpleX != board.getGoalX() || purpleY != board.getGoalY()) {
                            if (board.getBoard()[purpleX][purpleY] != 'G') {
                                board.getBoard()[purpleX][purpleY] = 'W';
                            }
                        } else {
                            board.getBoard()[purpleX][purpleY] = 'G';
                        }

                        board.setPurplePosition(purpleX, newPurpleY);
                        board.getBoard()[purpleX][newPurpleY] = 'P';
                    }
                }
            }
        } else {


            if (board.getRedX() >= 0 && board.getRedY() >= 0) {
                int redX = board.getRedX();
                int redY = board.getRedY();
                int newRedY = redY;

                while (newRedY < board.getBoard()[0].length - 1 &&
                        board.getBoard()[redX][newRedY + 1] != 'B' &&
                        board.getBoard()[redX][newRedY + 1] != 'R' &&
                        board.getBoard()[redX][newRedY + 1] != 'P') {
                    char nextCell = board.getBoard()[redX][newRedY + 1];
                    if (nextCell == 'G' && newRedY + 1 == board.getGoalY() && redX == board.getGoalX()) {
                        newRedY++;
                        break;
                   }
                    newRedY++;
                }

                if (newRedY != redY) {
                    // Clear old position
                    if (redX != board.getGoalX() || redY != board.getGoalY()) {
                        board.getBoard()[redX][redY] = 'W';
                    }

                    board.setRedPosition(redX, newRedY);

                    if (newRedY == board.getGoalY() && redX == board.getGoalX()) {
                        board.removeRedCube();
                        board.getBoard()[redX][newRedY] = 'W';
                    } else {
                        board.getBoard()[redX][newRedY] = 'R';
                    }
                }
            }

            if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
                int purpleX = board.getPurpleX();
                int purpleY = board.getPurpleY();
                int newPurpleY = purpleY;

                while (newPurpleY < board.getBoard()[0].length - 1 &&
                        board.getBoard()[purpleX][newPurpleY + 1] != 'B' &&
                        board.getBoard()[purpleX][newPurpleY + 1] != 'R' &&
                        board.getBoard()[purpleX][newPurpleY + 1] != 'P') {
                    char nextCell = board.getBoard()[purpleX][newPurpleY + 1];
                    if (nextCell == 'G' && newPurpleY + 1 == board.getGoalY() && purpleX == board.getGoalX()) {
                        newPurpleY++;
                        break;
                    }
                    newPurpleY++;
                }

                if (newPurpleY != purpleY) {
                    if (purpleX != board.getGoalX() || purpleY != board.getGoalY()) {
                        if (board.getBoard()[purpleX][purpleY] != 'G') {
                            board.getBoard()[purpleX][purpleY] = 'W';
                        }
                    } else {
                        board.getBoard()[purpleX][purpleY] = 'G';
                    }
                      board.getBoard()[purpleX][purpleY] = 'W';


                    board.setPurplePosition(purpleX, newPurpleY);


                    if (newPurpleY == board.getGoalYP() && purpleX == board.getGoalXP()) {
                        board.removePurpleCube();
                        board.getBoard()[purpleX][newPurpleY] = 'W';
                    } else {
                        board.getBoard()[purpleX][newPurpleY] = 'P';
                    }
                }
            }

            repaint();
        }

return board;
    }
    public Board moveLeft() {
        // Move Red Cube
        if (board.getRedX() >= 0 && board.getRedY() >= 0) {
            int redX = board.getRedX();
            int redY = board.getRedY();
            int newRedY = redY;

            while (newRedY > 0) {
                char nextCell = board.getBoard()[redX][newRedY - 1];

                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
                    break;
                }

                if (nextCell == 'G' && newRedY - 1 == board.getGoalY() && redX == board.getGoalX()) {
                    newRedY--;
                    break;
                }

                newRedY--;
            }

            if (newRedY != redY) {
                if (redX != board.getGoalX() || redY != board.getGoalY()) {
                    board.getBoard()[redX][redY] = 'W';
                }

                board.setRedPosition(redX, newRedY);

                if (newRedY == board.getGoalY() && redX == board.getGoalX()) {
                    board.removeRedCube();
                    board.getBoard()[redX][newRedY] = 'W';
                } else {
                    board.getBoard()[redX][newRedY] = 'R';
                }
            }
        }

        if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
            int purpleX = board.getPurpleX();
            int purpleY = board.getPurpleY();
            int newPurpleY = purpleY;

            while (newPurpleY > 0) {
                char nextCell = board.getBoard()[purpleX][newPurpleY - 1];

                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
                    break;
                }

                if (nextCell == 'G' && newPurpleY - 1 == board.getGoalYP() && purpleX == board.getGoalXP()) {
                    newPurpleY--;
                    break;
                }

                newPurpleY--;
            }

            if (newPurpleY != purpleY) {
                if (purpleX != board.getGoalXP() || purpleY != board.getGoalYP()) {
                    board.getBoard()[purpleX][purpleY] = 'W';
                }

                board.setPurplePosition(purpleX, newPurpleY);

                if (newPurpleY == board.getGoalYP() && purpleX == board.getGoalXP()) {
                    board.removePurpleCube();
                    board.getBoard()[purpleX][newPurpleY] = 'W';
                } else {
                    board.getBoard()[purpleX][newPurpleY] = 'P';
                }
            }
        }

        repaint();
        return board;
    }
    public Board moveUp() {
        // Move Red Cube
        if (board.getRedX() >= 0 && board.getRedY() >= 0) {
            int redX = board.getRedX();
            int redY = board.getRedY();
            int newRedX = redX;

            while (newRedX > 0) {
                char nextCell = board.getBoard()[newRedX - 1][redY];

                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
                    break;
                }

                if (nextCell == 'G' && newRedX - 1 == board.getGoalX() && redY == board.getGoalY()) {
                    newRedX--;
                    break;
                }

                newRedX--;
            }

            if (newRedX != redX) {
                // Clear old position
                if (redX != board.getGoalX() || redY != board.getGoalY()) {
                    board.getBoard()[redX][redY] = 'W';
                }

                board.setRedPosition(newRedX, redY);

                if (newRedX == board.getGoalX() && redY == board.getGoalY()) {
                    board.removeRedCube();
                    board.getBoard()[newRedX][redY] = 'W';
                } else {
                    board.getBoard()[newRedX][redY] = 'R';
                }
            }
        }

        if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
            int purpleX = board.getPurpleX();
            int purpleY = board.getPurpleY();
            int newPurpleX = purpleX;

            while (newPurpleX > 0) {
                char nextCell = board.getBoard()[newPurpleX - 1][purpleY];

                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
                    break;
                }

                if (nextCell == 'G' && newPurpleX - 1 == board.getGoalXP() && purpleY == board.getGoalYP()) {
                    newPurpleX--;
                    break;
                }

                newPurpleX--;
            }

            if (newPurpleX != purpleX) {
                if (purpleX != board.getGoalXP() || purpleY != board.getGoalYP()) {
                    //board.getBoard()[purpleX][purpleY] = 'W';
                    if (board.getBoard()[purpleX][purpleY] != 'G') {
                        board.getBoard()[purpleX][purpleY] = 'W';
                    }
                } else {
                    board.getBoard()[purpleX][purpleY] = 'G';

                }

                board.setPurplePosition(newPurpleX, purpleY);

                if (newPurpleX == board.getGoalXP() && purpleY == board.getGoalYP()) {
                    board.removePurpleCube();
                    board.getBoard()[newPurpleX][purpleY] = 'W';
                } else {
                    board.getBoard()[newPurpleX][purpleY] = 'P';
                }
            }
        }

        repaint();
        return board;
    }
    public Board moveDown() {
    if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
        int purpleX = board.getPurpleX();
        int purpleY = board.getPurpleY();

        int newPurpleX = purpleX;

        while (newPurpleX < board.getBoard().length - 1) {
            char nextCell = board.getBoard()[newPurpleX + 1][purpleY];

            if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
                break;
            }

            if (nextCell == 'G' &&
                    newPurpleX + 1 == board.getGoalXP() && purpleY == board.getGoalYP()) {
                newPurpleX++;
                break;
            }

            newPurpleX++;
        }

        if (newPurpleX != purpleX) {
            if (purpleX != board.getGoalXP() || purpleY != board.getGoalYP()) {
                board.getBoard()[purpleX][purpleY] = 'W';
            }

            board.setPurplePosition(newPurpleX, purpleY);

            if (newPurpleX == board.getGoalXP() && purpleY == board.getGoalYP()) {
                board.removePurpleCube();
                board.getBoard()[newPurpleX][purpleY] = 'W';

            } else {
                board.getBoard()[newPurpleX][purpleY] = 'P';
            }
        }
    }


    if (board.getRedX() >= 0 && board.getRedY() >= 0) {
        int redX = board.getRedX();
        int redY = board.getRedY();

        int newRedX = redX;

        while (newRedX < board.getBoard().length - 1) {
            char nextCell = board.getBoard()[newRedX + 1][redY];

            if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
                break;
            }

            if (nextCell == 'G' &&
                    newRedX + 1 == board.getGoalX() && redY == board.getGoalY()) {
                newRedX++; // التقدم إلى الهدف
                break;
            }

            newRedX++;
        }

        if (newRedX != redX) {
            if (redX != board.getGoalX() || redY != board.getGoalY()) {
                board.getBoard()[redX][redY] = 'W';
            }

            board.setRedPosition(newRedX, redY);

            if (newRedX == board.getGoalX() && redY == board.getGoalY()) {
                board.getBoard()[newRedX][redY] = 'G';
            } else {
                board.getBoard()[newRedX][redY] = 'R';
            }
        }
    }

    repaint();
        return board;
}
    public void move(String direction) {
        switch (direction.toLowerCase()) {
            case "left":
                moveLeft();
                break;
            case "right":
                moveRight();
                break;
        case "up":
            moveUp();
            break;
        case "down":
            moveDown();
            break;
        default:
            System.out.println("Invalid direction");
            return;

        }

        board.updateBoardAfterMove();
        repaint();
    }

    private static void printBoard(Board board) {
        char[][] grid = board.getBoard();
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {


        JFrame frame = new JFrame("zero squares");
       // Board tempBoard = new Board(1);
       // StateDFS gameState2 = new StateDFS();
        State2 gameState = new State2();
        frame.add(gameState);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        /////////////////dfs///////////////////////////////
        DFSSolver solver = new DFSSolver();
        List<String> solution = solver.solve(gameState);

        if (solution.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution found:");
            for (String move : solution) {
                System.out.println(move);

            }
            System.out.println("---------------------DFS-----------------------------");

        }
        /////////////////////////////////////////////
        /////////////////dfs///////////////////////////////
        DFSSolverR solverRE = new DFSSolverR();
        List<String> solutionRE = solverRE.solve(gameState);

        if (solutionRE.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution found:");
            for (String move : solutionRE) {
                System.out.println(move);

            }
            System.out.println("---------------------DFSR-----------------------------");

        }
        /////////////////////////////////////////////
        //////////////////////bfs//////////////////
        BFSSolver solverB = new BFSSolver();
        List<String> solutionB = solverB.solve(gameState);

        if (solutionB.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution found:");
            for (String move : solutionB) {
                System.out.println(move);

            }
            System.out.println("---------------------BFS-----------------------------");

        }

        //////////////////////////////////////////
        //////////////////////////UCS/////////////////
        UCSSolver solverU = new UCSSolver();
        List<String> solutionU = solverU.solve(gameState);

        if (solutionU.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution found:");
            for (String move : solutionU) {
                System.out.println(move);
            }
            System.out.println("---------------------ucs-----------------------------");

        }
        /////////////////////////////////////////////


        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                if (gameState.checkGoal()) {
                    if (gameState.level < 5) {
                        gameState.level++;
                        gameState.board.initializeBoard(gameState.level);
                        gameState.repaint();
                    }

                    else {
                        JOptionPane.showMessageDialog(frame, "You completed all levels!");
                    }
                    return;
                }
               // gameState2.dfsSolve();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> gameState.move("up");
                    case KeyEvent.VK_DOWN -> gameState.move("down");
                    case KeyEvent.VK_LEFT -> gameState.move("left");
                    case KeyEvent.VK_RIGHT -> gameState.move("right");
                }
                if (gameState.checkGoal()) {
                    JOptionPane.showMessageDialog(frame, "Congratulations! You reached the goal!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}


/////////////////////////////////////////////////////////////////



















