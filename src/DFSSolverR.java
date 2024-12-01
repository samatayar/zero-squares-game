import java.util.*;

public class DFSSolverR {

    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};

    private Set<String> visitedStates;
    private List<String> solutionPath;

    public DFSSolverR() {
        this.visitedStates = new HashSet<>();
        this.solutionPath = new ArrayList<>();
    }

    public List<String> solve(State2 gameState) {
        visitedStates.clear();
        solutionPath.clear();

        if (dfs(gameState, "")) {
            return solutionPath;
        }
        return Collections.emptyList();
    }

    private boolean dfs(State2 gameState, String lastMove) {
        String currentState = encodeState(gameState.board);

        if (visitedStates.contains(currentState)) {
            return false;
        }
        visitedStates.add(currentState);

        if (gameState.checkGoal(gameState.board)) {
            return true;
        }

        for (String direction : DIRECTIONS) {
            if (!direction.equals(oppositeMove(lastMove))) {
                State2 newState = cloneState(gameState);
                newState.move(direction);

                if (!encodeState(newState.board).equals(currentState)) {
                    if (dfs(newState, direction)) {
                        solutionPath.add(0, direction);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private String encodeState(Board board) {
        StringBuilder sb = new StringBuilder();
        char[][] gameBoard = board.getBoard();
        for (char[] row : gameBoard) {
            for (char cell : row) {
                sb.append(cell);
            }
        }
        return sb.toString();
    }

    private String oppositeMove(String move) {
        return switch (move) {
            case "up" -> "down";
            case "down" -> "up";
            case "left" -> "right";
            case "right" -> "left";
            default -> "";
        };
    }

    private State2 cloneState(State2 original) {
        State2 clonedState = new State2();
        clonedState.board = original.board.cloneState();
        return clonedState;
    }
}
