import java.util.*;

public class BFSSolver {

    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};

    public List<String> solve(State2 gameState) {
        Set<String> visitedStates = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(gameState, new ArrayList<>()));
        visitedStates.add(encodeState(gameState.board));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            State2 currentState = currentNode.state;
            List<String> currentPath = currentNode.path;

            if (currentState.checkGoal()) {
                return currentPath;
            }

            for (String direction : DIRECTIONS) {
                State2 newState = cloneState(currentState);
                newState.move(direction);

                String encodedState = encodeState(newState.board);
                if (!visitedStates.contains(encodedState)) {
                    visitedStates.add(encodedState);
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(direction);
                    queue.add(new Node(newState, newPath));
                }
            }
        }

        return Collections.emptyList();
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

    private static class Node {
        State2 state;
        List<String> path;

        Node(State2 state, List<String> path) {
            this.state = state;
            this.path = path;
        }
    }
}
