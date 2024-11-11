import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class State extends JPanel {
    private Board board;
    private  int level = 1;

    public State() {
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


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        char[][] gameBoard = board.getBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (i == board.getGoalX() && j == board.getGoalY() && !(board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY())) {

                    g.setColor(Color.WHITE);
                    g.fillRect(j * 50, i * 50, 50, 50);
                    g.setColor(Color.RED);
                    g.drawRect(j * 50, i * 50, 50, 50);
                } else if (!board.isSingleCube() && i == board.getGoalXP() && j == board.getGoalYP() && !(board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP())) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * 50, i * 50, 50, 50);
                    g.setColor(new Color(128, 0, 128));
                    g.drawRect(j * 50, i * 50, 50, 50);
                } else {

                    g.setColor(switch (gameBoard[i][j]) {
                        case 'B' -> Color.BLACK;
                        case 'R' -> Color.RED;
                        case 'P' -> new Color(128, 0, 128);
                        default -> Color.WHITE;
                    });
                    g.fillRect(j * 50, i * 50, 50, 50);
                    g.setColor(Color.GRAY);
                    g.drawRect(j * 50, i * 50, 50, 50);
                }
                if (i == board.getRedX() && j == board.getRedY() && !(board.getRedX() == board.getGoalXP() && board.getRedY() == board.getGoalYP() && board.isSingleCube())) {
                    g.setColor(Color.RED);
                    g.fillRect(j * 50, i * 50, 50, 50);
                }

                if (!board.isSingleCube() && i == board.getPurpleX() && j == board.getPurpleY() && !(board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP())) {
                    g.setColor(new Color(128, 0, 128));
                    g.fillRect(j * 50, i * 50, 50, 50);
                }
                //////////////////////وبرضوووووووووووو هووووووووووووووووون بس متل قلتو
                if (i == board.getRedX() && j == board.getRedY() && !(board.getRedX() == board.getGoalXP() && board.getRedY() == board.getGoalY())) {
                    g.setColor(Color.RED);
                    g.fillRect(j * 50, i * 50, 50, 50);
                }
                /////////////////////////////////////////////
            }
        }
    }




    public Point moveUp(int x, int y, int goalX, int goalY, int otherX, int otherY) {
        for (int i = x - 1; i >= 0; i--) {
            if (board.getBoard()[i][y] == 'B' || (i == otherX && y == otherY)) break;
            x = i;
            if (x == goalX && y == goalY) break;
        }
        return new Point(x, y);
    }

    public Point moveDown(int x, int y, int goalX, int goalY, int otherX, int otherY) {
        for (int i = x + 1; i < board.getBoard().length; i++) {
            if (board.getBoard()[i][y] == 'B' || (i == otherX && y == otherY)) break;
            x = i;
            if (x == goalX && y == goalY) break;
        }
        return new Point(x, y);
    }

    public Point moveLeft(int x, int y, int goalX, int goalY, int otherX, int otherY) {
        for (int i = y - 1; i >= 0; i--) {
            if (board.getBoard()[x][i] == 'B' || (x == otherX && i == otherY)) break;
            y = i;
            if (x == goalX && y == goalY) break;
        }
        return new Point(x, y);
    }

    public Point moveRight(int x, int y, int goalX, int goalY, int otherX, int otherY) {
        for (int i = y + 1; i < board.getBoard()[0].length; i++) {
            if (board.getBoard()[x][i] == 'B' || (x == otherX && i == otherY)) break;
            y = i;
            if (x == goalX && y == goalY) break;
        }
        return new Point(x, y);
    }

    public void move(String direction) {
        if (board.isSingleCube()) {
            moveSingleCube(direction);
        } else {
            moveTwoCubes(direction);
        }
        board.updateBoardAfterMove();
        repaint();
    }


    private void moveSingleCube(String direction) {
        board.setBoardCell(board.getRedX(), board.getRedY(), 'W');
        Point newRedPosition = new Point(board.getRedX(), board.getRedY());

        switch (direction) {
            case "up" -> newRedPosition = moveUp(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
            case "down" -> newRedPosition = moveDown(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
            case "left" -> newRedPosition = moveLeft(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
            case "right" -> newRedPosition = moveRight(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
        }

        board.setRedX(newRedPosition.x);
        board.setRedY(newRedPosition.y);
        if (!(newRedPosition.x == board.getGoalX() && newRedPosition.y == board.getGoalY())) {
            board.setBoardCell(newRedPosition.x, newRedPosition.y, 'R');
        }

        repaint();
    }


    private void moveTwoCubes(String direction) {
        Point newRedPosition = new Point(board.getRedX(), board.getRedY());
        Point newPurplePosition = new Point(board.getPurpleX(), board.getPurpleY());

        switch (direction) {
            case "up" -> {
                if (!(board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY())) {
                    newRedPosition = moveUp(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                }
                if (!(board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP())) {
                    newPurplePosition = moveUp(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                }
            }
            case "down" -> {
                if (!(board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY())) {
                    newRedPosition = moveDown(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                }
                if (!(board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP())) {
                    newPurplePosition = moveDown(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                }
            }
            case "left" -> {
                if (!(board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY())) {
                    newRedPosition = moveLeft(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                }
                if (!(board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP())) {
                    newPurplePosition = moveLeft(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                }
            }
            case "right" -> {
                if (!(board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY())) {
                    newRedPosition = moveRight(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                }
                if (!(board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP())) {
                    newPurplePosition = moveRight(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                }
            }
        }

        if (!(board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY())) {
            board.setBoardCell(board.getRedX(), board.getRedY(), 'W');
        }
        if (!(board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP())) {
            board.setBoardCell(board.getPurpleX(), board.getPurpleY(), 'W');
        }

        board.setRedX(newRedPosition.x);
        board.setRedY(newRedPosition.y);
        board.setPurpleX(newPurplePosition.x);
        board.setPurpleY(newPurplePosition.y);



        repaint();
    }


    public List<char[][]> nextState() {
        List<char[][]> possibleStates = new ArrayList<>();
        String[] directions = {"up", "down", "left", "right"};

        if (board.isSingleCube()) {
            for (String direction : directions) {
                Board stateBoard = new Board(1);
                stateBoard.setRedX(board.getRedX());
                stateBoard.setRedY(board.getRedY());
                stateBoard.initializeBoard(level);
                Point newRedPosition = new Point(board.getRedX(), board.getRedY());

                switch (direction) {
                    case "up" -> newRedPosition = moveUp(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), -1, -1);
                    case "down" -> newRedPosition = moveDown(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), -1, -1);
                    case "left" -> newRedPosition = moveLeft(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), -1, -1);
                    case "right" -> newRedPosition = moveRight(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), -1, -1);
                }

                if (newRedPosition.x != stateBoard.getRedX() || newRedPosition.y != stateBoard.getRedY()) {
                    stateBoard.setBoardCell(stateBoard.getRedX(), stateBoard.getRedY(), 'W');
                    stateBoard.setBoardCell(newRedPosition.x, newRedPosition.y, 'R');
                    stateBoard.setRedX(newRedPosition.x);
                    stateBoard.setRedY(newRedPosition.y);

                    possibleStates.add(Board.copyBoard(stateBoard.getBoard()));
                }
                // تحقق إذا كانت هذه الحالة الجديدة غير مكررة قبل الإضافة
                if (!possibleStates.contains(Board.copyBoard(stateBoard.getBoard()))) {
                    possibleStates.add(Board.copyBoard(stateBoard.getBoard()));
                }
            }
        } else {
            for (String direction : directions) {
                Board stateBoard = new Board(1);
                stateBoard.setRedX(board.getRedX());
                stateBoard.setRedY(board.getRedY());
                stateBoard.setPurpleX(board.getPurpleX());
                stateBoard.setPurpleY(board.getPurpleY());
                stateBoard.initializeBoard(level);

                Point newRedPosition = new Point(board.getRedX(), board.getRedY());
                Point newPurplePosition = new Point(board.getPurpleX(), board.getPurpleY());

                switch (direction) {
                    case "up" -> {
                        newRedPosition = moveUp(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                        newPurplePosition = moveUp(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                    }
                    case "down" -> {
                        newRedPosition = moveDown(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                        newPurplePosition = moveDown(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                    }
                    case "left" -> {
                        newRedPosition = moveLeft(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                        newPurplePosition = moveLeft(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                    }
                    case "right" -> {
                        newRedPosition = moveRight(board.getRedX(), board.getRedY(), board.getGoalX(), board.getGoalY(), board.getPurpleX(), board.getPurpleY());
                        newPurplePosition = moveRight(board.getPurpleX(), board.getPurpleY(), board.getGoalXP(), board.getGoalYP(), board.getRedX(), board.getRedY());
                    }
                }

                if (newRedPosition.x != board.getRedX() || newRedPosition.y != board.getRedY() ||
                        newPurplePosition.x != board.getPurpleX() || newPurplePosition.y != board.getPurpleY()) {

                    stateBoard.setBoardCell(stateBoard.getRedX(), stateBoard.getRedY(), 'W');
                    stateBoard.setBoardCell(stateBoard.getPurpleX(), stateBoard.getPurpleY(), 'W');

                    stateBoard.setRedX(newRedPosition.x);
                    stateBoard.setRedY(newRedPosition.y);
                    stateBoard.setPurpleX(newPurplePosition.x);
                    stateBoard.setPurpleY(newPurplePosition.y);

                    stateBoard.setBoardCell(newRedPosition.x, newRedPosition.y, 'R');
                    stateBoard.setBoardCell(newPurplePosition.x, newPurplePosition.y, 'P');

                    possibleStates.add(Board.copyBoard(stateBoard.getBoard()));
                }
            }
        }
        return possibleStates;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("zero squares");
        State gameState = new State();
        frame.add(gameState);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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
                    } else {
                        JOptionPane.showMessageDialog(frame, "You completed all levels!");
                    }
                    return;
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> gameState.move("up");
                    case KeyEvent.VK_DOWN -> gameState.move("down");
                    case KeyEvent.VK_LEFT -> gameState.move("left");
                    case KeyEvent.VK_RIGHT -> gameState.move("right");
                }

                List<char[][]> possibleStates = gameState.nextState();
                System.out.println("Possible next states:");
                for (char[][] state : possibleStates) {
                    printBoard(state);
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
    }}



