import java.util.*;

public class HillClimbingSolver {

    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};

    public List<String> solve(State2 gameState) {
        List<String> solutionPath = new ArrayList<>();
        Set<String> visitedStates = new HashSet<>();

        State2 currentState = gameState;
        visitedStates.add(encodeState(currentState.board));

        while (true) {
            if (currentState.checkGoal(currentState.board)) {
                return solutionPath;
            }

            String bestDirection = null;
            State2 bestState = null;
            int bestHeuristic = Integer.MAX_VALUE;

            for (String direction : DIRECTIONS) {
                State2 newState = cloneState(currentState);
                newState.move(direction);

                String encodedState = encodeState(newState.board);
                if (!visitedStates.contains(encodedState)) {
                    int heuristic = calculateHeuristic(newState);
                    if (heuristic < bestHeuristic) {
                        bestHeuristic = heuristic;
                        bestState = newState;
                        bestDirection = direction;
                    }
                }
            }

            if (bestState == null) {
                break;
            }

            currentState = bestState;
            visitedStates.add(encodeState(currentState.board));
            solutionPath.add(bestDirection);
        }

        return Collections.emptyList();
    }

    private int calculateHeuristic(State2 state) {
        return state.getHeuristicValue();
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

    private State2 cloneState(State2 original) {
        State2 clonedState = new State2();
        clonedState.board = original.board.cloneState();
        return clonedState;
    }
}
