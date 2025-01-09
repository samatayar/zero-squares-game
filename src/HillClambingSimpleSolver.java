import java.util.*;

public class HillClambingSimpleSolver {

    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};
    private int visitedNodesCount = 0;

    public List<String> solve(State2 gameState) {
        List<String> solutionPath = new ArrayList<>();
        Set<String> visitedStates = new HashSet<>();

        State2 currentState = gameState;
        visitedStates.add(encodeState(currentState.board));

        while (true) {
            if (currentState.checkGoal(currentState.board)) {
                System.out.println("Visited nodes: " + visitedNodesCount);
                return solutionPath;
            }

            boolean moved = false;

            for (String direction : DIRECTIONS) {
                visitedNodesCount++;

                State2 newState = cloneState(currentState);
                newState.move(direction);

                String encodedState = encodeState(newState.board);
                if (!visitedStates.contains(encodedState)) {
                    int heuristic = calculateHeuristic(newState);

                    if (heuristic < calculateHeuristic(currentState)) {
                        currentState = newState;
                        visitedStates.add(encodedState);
                        solutionPath.add(direction);
                        moved = true;
                        break;
                    }
                }
            }

            if (!moved) {
                System.out.println("Visited nodes: " + visitedNodesCount);
                break;
            }
        }

        return Collections.emptyList();
    }

    private int calculateHeuristic(State2 state) {
        return state.getHeuristicValue(); // Modify as needed
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
