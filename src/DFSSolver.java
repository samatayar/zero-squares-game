//import java.util.*;
//
//public class DFSSolver {
//    private Board board; // الحالة الحالية للوحة
//    private final Stack<String> moveStack = new Stack<>(); // لتتبع الحركات أثناء البحث
//
//    public DFSSolver(int level) {
//        board = new Board(level); // بدء اللعبة بالمستوى المعطى
//    }
//
//    // تحقق ما إذا كان الهدف قد تحقق
//    private boolean isGoalReached(Board board) {
//        if (board.isSingleCube()) {
//            return board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY();
//        } else {
//            boolean redAtGoal = board.getRedX() == board.getGoalX() && board.getRedY() == board.getGoalY();
//            boolean purpleAtGoal = board.getPurpleX() == board.getGoalXP() && board.getPurpleY() == board.getGoalYP();
//            return redAtGoal && purpleAtGoal;
//        }
//    }
//
//    // نسخ عميق للكائن Board لتجنب تغيير الحالة الأصلية أثناء البحث
//    private Board copyBoard(Board board) {
//        return Board.copyBoard(board);
//    }
//    public Board   moveRight() {
//        if (Math.abs(board.getPurpleY() - board.getRedY()) == 1) {
//            if (board.getRedY() > board.getPurpleY()) {
//                // Move Red Cube
//                if (board.getRedX() >= 0 && board.getRedY() >= 0) {
//                    int redX = board.getRedX();
//                    int redY = board.getRedY();
//
//                    int newRedY = redY;
//                    while (newRedY < board.getBoard()[0].length - 1 &&
//                            board.getBoard()[redX][newRedY + 1] != 'B' &&
//                            board.getBoard()[redX][newRedY + 1] != 'R' &&
//                            board.getBoard()[redX][newRedY + 1] != 'P') {
//                        newRedY++;
//                    }
//
//                    if (newRedY != redY) {
//                        // Clear the old position
//                        if (redX != board.getGoalX() || redY != board.getGoalY()) {
//                            if (board.getBoard()[redX][redY] != 'G') {
//                                board.getBoard()[redX][redY] = 'W';
//                            }
//                        } else {
//                            board.getBoard()[redX][redY] = 'G';
//                        }
//
//                        board.setRedPosition(redX, newRedY);
//                        board.getBoard()[redX][newRedY] = 'R';
//                    }
//                }
//            } else if (board.getPurpleY() > board.getRedY()) {
//                // Move Purple Cube
//                if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
//                    int purpleX = board.getPurpleX();
//                    int purpleY = board.getPurpleY();
////////////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                    if (purpleY == board.getRedY() + 1) {
//                        return board;
//                    }
///////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
//                    int newPurpleY = purpleY;
//                    while (newPurpleY < board.getBoard()[0].length - 1 &&
//                            board.getBoard()[purpleX][newPurpleY + 1] != 'B' &&
//                            board.getBoard()[purpleX][newPurpleY + 1] != 'R' &&
//                            board.getBoard()[purpleX][newPurpleY + 1] != 'P') {
//                        newPurpleY++;
//                    }
//
//                    if (newPurpleY != purpleY) {
//                        // Clear the old position
//                        if (purpleX != board.getGoalX() || purpleY != board.getGoalY()) {
//                            if (board.getBoard()[purpleX][purpleY] != 'G') {
//                                board.getBoard()[purpleX][purpleY] = 'W';
//                            }
//                        } else {
//                            board.getBoard()[purpleX][purpleY] = 'G';
//                        }
//
//                        board.setPurplePosition(purpleX, newPurpleY);
//                        board.getBoard()[purpleX][newPurpleY] = 'P';
//                    }
//                }
//            }
//        } else {
//
//
//            if (board.getRedX() >= 0 && board.getRedY() >= 0) {
//                int redX = board.getRedX();
//                int redY = board.getRedY();
//                int newRedY = redY;
//
//                while (newRedY < board.getBoard()[0].length - 1 &&
//                        board.getBoard()[redX][newRedY + 1] != 'B' &&
//                        board.getBoard()[redX][newRedY + 1] != 'R' &&
//                        board.getBoard()[redX][newRedY + 1] != 'P') {
//                    char nextCell = board.getBoard()[redX][newRedY + 1];
//                    if (nextCell == 'G' && newRedY + 1 == board.getGoalY() && redX == board.getGoalX()) {
//                        newRedY++;
//                        break;
//                    }
//                    newRedY++;
//                }
//
//                if (newRedY != redY) {
//                    // Clear old position
//                    if (redX != board.getGoalX() || redY != board.getGoalY()) {
//                        board.getBoard()[redX][redY] = 'W';
//                    }
//
//                    board.setRedPosition(redX, newRedY);
//
//                    if (newRedY == board.getGoalY() && redX == board.getGoalX()) {
//                        board.removeRedCube();
//                        board.getBoard()[redX][newRedY] = 'W';
//                    } else {
//                        board.getBoard()[redX][newRedY] = 'R';
//                    }
//                }
//            }
//
//            if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
//                int purpleX = board.getPurpleX();
//                int purpleY = board.getPurpleY();
//                int newPurpleY = purpleY;
//
//                while (newPurpleY < board.getBoard()[0].length - 1 &&
//                        board.getBoard()[purpleX][newPurpleY + 1] != 'B' &&
//                        board.getBoard()[purpleX][newPurpleY + 1] != 'R' &&
//                        board.getBoard()[purpleX][newPurpleY + 1] != 'P') {
//                    char nextCell = board.getBoard()[purpleX][newPurpleY + 1];
//                    if (nextCell == 'G' && newPurpleY + 1 == board.getGoalY() && purpleX == board.getGoalX()) {
//                        newPurpleY++;
//                        break;
//                    }
//                    newPurpleY++;
//                }
//
//                if (newPurpleY != purpleY) {
//                    if (purpleX != board.getGoalX() || purpleY != board.getGoalY()) {
//                        if (board.getBoard()[purpleX][purpleY] != 'G') {
//                            board.getBoard()[purpleX][purpleY] = 'W';
//                        }
//                    } else {
//                        board.getBoard()[purpleX][purpleY] = 'G';
//                    }
//                    board.getBoard()[purpleX][purpleY] = 'W';
//
//
//                    board.setPurplePosition(purpleX, newPurpleY);
//
//
//                    if (newPurpleY == board.getGoalYP() && purpleX == board.getGoalXP()) {
//                        board.removePurpleCube();
//                        board.getBoard()[purpleX][newPurpleY] = 'W';
//                    } else {
//                        board.getBoard()[purpleX][newPurpleY] = 'P';
//                    }
//                }
//            }
//
//
//        }
//
//        return board;
//    }
//    public Board moveLeft() {
//        // Move Red Cube
//        if (board.getRedX() >= 0 && board.getRedY() >= 0) {
//            int redX = board.getRedX();
//            int redY = board.getRedY();
//            int newRedY = redY;
//
//            while (newRedY > 0) {
//                char nextCell = board.getBoard()[redX][newRedY - 1];
//
//                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
//                    break; // Stop at obstacles
//                }
//
//                if (nextCell == 'G' && newRedY - 1 == board.getGoalY() && redX == board.getGoalX()) {
//                    newRedY--;
//                    break; // Stop at the goal
//                }
//
//                newRedY--;
//            }
//
//            if (newRedY != redY) {
//                // Clear old position
//                if (redX != board.getGoalX() || redY != board.getGoalY()) {
//                    board.getBoard()[redX][redY] = 'W';
//                }
//
//                board.setRedPosition(redX, newRedY);
//
//                if (newRedY == board.getGoalY() && redX == board.getGoalX()) {
//                    board.removeRedCube(); // Remove cube at goal
//                    board.getBoard()[redX][newRedY] = 'W';
//                } else {
//                    board.getBoard()[redX][newRedY] = 'R';
//                }
//            }
//        }
//
//        // Move Purple Cube
//        if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
//            int purpleX = board.getPurpleX();
//            int purpleY = board.getPurpleY();
//            int newPurpleY = purpleY;
//
//            while (newPurpleY > 0) {
//                char nextCell = board.getBoard()[purpleX][newPurpleY - 1];
//
//                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
//                    break; // Stop at obstacles
//                }
//
//                if (nextCell == 'G' && newPurpleY - 1 == board.getGoalYP() && purpleX == board.getGoalXP()) {
//                    newPurpleY--;
//                    break; // Stop at the goal
//                }
//
//                newPurpleY--;
//            }
//
//            if (newPurpleY != purpleY) {
//                if (purpleX != board.getGoalXP() || purpleY != board.getGoalYP()) {
//                    board.getBoard()[purpleX][purpleY] = 'W';
//                }
//
//                board.setPurplePosition(purpleX, newPurpleY);
//
//                if (newPurpleY == board.getGoalYP() && purpleX == board.getGoalXP()) {
//                    board.removePurpleCube(); // Remove cube at goal
//                    board.getBoard()[purpleX][newPurpleY] = 'W';
//                } else {
//                    board.getBoard()[purpleX][newPurpleY] = 'P';
//                }
//            }
//        }
//
//
//        return board;
//    }
//    public Board moveUp() {
//        // Move Red Cube
//        if (board.getRedX() >= 0 && board.getRedY() >= 0) {
//            int redX = board.getRedX();
//            int redY = board.getRedY();
//            int newRedX = redX;
//
//            while (newRedX > 0) {
//                char nextCell = board.getBoard()[newRedX - 1][redY];
//
//                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
//                    break; // Stop at obstacles
//                }
//
//                if (nextCell == 'G' && newRedX - 1 == board.getGoalX() && redY == board.getGoalY()) {
//                    newRedX--;
//                    break; // Stop at the goal
//                }
//
//                newRedX--;
//            }
//
//            if (newRedX != redX) {
//                // Clear old position
//                if (redX != board.getGoalX() || redY != board.getGoalY()) {
//                    board.getBoard()[redX][redY] = 'W';
//                }
//
//                board.setRedPosition(newRedX, redY);
//
//                if (newRedX == board.getGoalX() && redY == board.getGoalY()) {
//                    board.removeRedCube(); // Remove cube at goal
//                    board.getBoard()[newRedX][redY] = 'W';
//                } else {
//                    board.getBoard()[newRedX][redY] = 'R';
//                }
//            }
//        }
//
//        // Move Purple Cube
//        if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
//            int purpleX = board.getPurpleX();
//            int purpleY = board.getPurpleY();
//            int newPurpleX = purpleX;
//
//            while (newPurpleX > 0) {
//                char nextCell = board.getBoard()[newPurpleX - 1][purpleY];
//
//                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
//                    break; // Stop at obstacles
//                }
//
//                if (nextCell == 'G' && newPurpleX - 1 == board.getGoalXP() && purpleY == board.getGoalYP()) {
//                    newPurpleX--;
//                    break; // Stop at the goal
//                }
//
//                newPurpleX--;
//            }
//
//            if (newPurpleX != purpleX) {
//                if (purpleX != board.getGoalXP() || purpleY != board.getGoalYP()) {
//                    //board.getBoard()[purpleX][purpleY] = 'W';
//                    if (board.getBoard()[purpleX][purpleY] != 'G') {
//                        board.getBoard()[purpleX][purpleY] = 'W';
//                    }
//                } else {
//                    board.getBoard()[purpleX][purpleY] = 'G';
//
//                }
//
//                board.setPurplePosition(newPurpleX, purpleY);
//
//                if (newPurpleX == board.getGoalXP() && purpleY == board.getGoalYP()) {
//                    board.removePurpleCube(); // Remove cube at goal
//                    board.getBoard()[newPurpleX][purpleY] = 'W';
//                } else {
//                    board.getBoard()[newPurpleX][purpleY] = 'P';
//                }
//            }
//        }
//
//
//        return board;
//    }
//    public Board moveDown() {
//        // Move Purple Cube
//        if (!board.isSingleCube() && board.getPurpleX() >= 0 && board.getPurpleY() >= 0) {
//            int purpleX = board.getPurpleX();
//            int purpleY = board.getPurpleY();
//
//            int newPurpleX = purpleX;
//
//            while (newPurpleX < board.getBoard().length - 1) {
//                char nextCell = board.getBoard()[newPurpleX + 1][purpleY];
//
//                // إذا كانت الخلية التالية عائقًا (جدار أو مكعب آخر)
//                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
//                    break; // لا يمكن التحرك للأسفل
//                }
//
//                // إذا كانت الخلية التالية هدف المكعب الموف
//                if (nextCell == 'G' &&
//                        newPurpleX + 1 == board.getGoalXP() && purpleY == board.getGoalYP()) {
//                    newPurpleX++; // التقدم إلى الهدف
//                    break; // توقف عند الهدف
//                }
//
//                // التقدم للأسفل
//                newPurpleX++;
//            }
//
//            // تحديث الموقع إذا تغير
//            if (newPurpleX != purpleX) {
//                // مسح الموقع القديم إذا لم يكن الهدف
//                if (purpleX != board.getGoalXP() || purpleY != board.getGoalYP()) {
//                    board.getBoard()[purpleX][purpleY] = 'W';
//                }
//
//                // نقل المكعب إلى الموقع الجديد
//                board.setPurplePosition(newPurpleX, purpleY);
//
//                // إذا كان الموقع الجديد هو الهدف
//                if (newPurpleX == board.getGoalXP() && purpleY == board.getGoalYP()) {
//                    board.removePurpleCube();
//                    board.getBoard()[newPurpleX][purpleY] = 'W';
//
//                } else {
//                    board.getBoard()[newPurpleX][purpleY] = 'P';
//                }
//            }
//        }
//
//        // Move Red Cube
//        if (board.getRedX() >= 0 && board.getRedY() >= 0) {
//            int redX = board.getRedX();
//            int redY = board.getRedY();
//
//            int newRedX = redX;
//
//            while (newRedX < board.getBoard().length - 1) {
//                char nextCell = board.getBoard()[newRedX + 1][redY];
//
//                // إذا كانت الخلية التالية عائقًا (جدار أو مكعب آخر)
//                if (nextCell == 'B' || nextCell == 'R' || nextCell == 'P') {
//                    break; // لا يمكن التحرك للأسفل
//                }
//
//                // إذا كانت الخلية التالية هدف المكعب الأحمر
//                if (nextCell == 'G' &&
//                        newRedX + 1 == board.getGoalX() && redY == board.getGoalY()) {
//                    newRedX++; // التقدم إلى الهدف
//                    break; // توقف عند الهدف
//                }
//
//                // التقدم للأسفل
//                newRedX++;
//            }
//
//            if (newRedX != redX) {
//                // مسح الموقع القديم إذا لم يكن الهدف
//                if (redX != board.getGoalX() || redY != board.getGoalY()) {
//                    board.getBoard()[redX][redY] = 'W';
//                }
//
//                // نقل المكعب إلى الموقع الجديد
//                board.setRedPosition(newRedX, redY);
//
//                // إذا كان الموقع الجديد هو الهدف
//                if (newRedX == board.getGoalX() && redY == board.getGoalY()) {
//                    board.getBoard()[newRedX][redY] = 'G';
//                } else {
//                    board.getBoard()[newRedX][redY] = 'R';
//                }
//            }
//        }
//
//
//        return board;
//    }
//    // تنفيذ حركة بناءً على الاتجاه
//    private boolean move(Board board, String direction) {
//        switch (direction) {
//            case "up" -> moveUp();
//            case "down" -> moveDown();
//            case "left" -> moveLeft();
//            case "right" -> moveRight();
//            default -> {
//                return false; // اتجاه غير صالح
//            }
//        }
//        return true;
//    }
//
//    // خوارزمية البحث العميق (DFS)
//    public boolean solve() {
//        return dfs(board, new HashSet<>(), new ArrayList<>()); // بدء البحث من الحالة الحالية
//    }
//
//    private boolean dfs(Board currentBoard, Set<String> visited, List<String> path) {
//        // تحقق إذا كانت الحالة النهائية قد وصلت
//        if (isGoalReached(currentBoard)) {
//            System.out.println("Path to solution:");
//            for (String move : path) {
//                System.out.println(move);
//            }
//            return true;
//        }
//
//        // تمثيل الحالة الحالية كـ String لتجنب التكرار
//        String stateKey = Arrays.deepToString(currentBoard.getBoard());
//        if (visited.contains(stateKey)) {
//            return false; // تم زيارة هذه الحالة مسبقًا
//        }
//        visited.add(stateKey); // أضف الحالة إلى مجموعة الزيارات
//
//        // طباعة الحالة الحالية
//        System.out.println("Current Board:");
//        printBoard(currentBoard);
//        System.out.println("Path so far: " + path);
//
//        // جرب كل الحركات الممكنة
//        List<String> directions = List.of("up", "down", "left", "right");
//        for (String direction : directions) {
//            Board nextBoard = copyBoard(currentBoard); // نسخة جديدة لتجربة الحركة
//            if (move(nextBoard, direction)) {
//                path.add(direction); // أضف الحركة إلى المسار
//                if (dfs(nextBoard, visited, path)) {
//                    return true; // الحل وجد في هذا الفرع
//                }
//                path.remove(path.size() - 1); // التراجع إذا لم يتم العثور على الحل
//            }
//        }
//        return false; // لا يوجد حل في هذا المسار
//    }
//
//    // طباعة الحالة الحالية للوحة
//    private void printBoard(Board board) {
//        char[][] grid = board.getBoard();
//        for (char[] row : grid) {
//            for (char cell : row) {
//                System.out.print(cell + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        int level = 2; // اختر المستوى
//        DFSSolver solver = new DFSSolver(level);
//        if (!solver.solve()) {
//            System.out.println("No solution found for level " + level);
//        }
//    }
//}
//////////////////////////////////
import java.util.*;

public class DFSSolver {
    private boolean solved;
    private List<String> solutionPath;

    public DFSSolver() {
        this.solved = false;
        this.solutionPath = new ArrayList<>();
    }

    public List<String> solve(State2 initialState) {
        Set<String> visited = new HashSet<>();
        dfs(initialState, new ArrayList<>(), visited);
        return solutionPath;
    }

    private void dfs(State2 state, List<String> path, Set<String> visited) {
        if (solved) return;
        if (state.checkGoal()) {
            solutionPath = new ArrayList<>(path);
            solved = true;
            return;
        }



        String stateID = generateStateID(state.board);

        if (visited.contains(stateID)) return;

        visited.add(stateID);

        String[] directions = {"up", "down", "left", "right"};
        for (String direction : directions) {
            State2 nextState = copyState(state);
            nextState.move(direction);
            path.add(direction);

            dfs(nextState, path, visited);

            path.remove(path.size() - 1);
        }
    }

    private String generateStateID(Board board) {
        char[][] currentBoard = board.getBoard();
        StringBuilder sb = new StringBuilder();
        for (char[] row : currentBoard) {
            for (char cell : row) {
                sb.append(cell);
            }
        }
        return sb.toString();
    }

    private State2 copyState(State2 state) {
        State2 newState = new State2();
        newState.board = Board.copyBoard(state.board);
        return newState;
    }


}
