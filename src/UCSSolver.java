import java.util.*;

public class UCSSolver {

    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};

    private static final Map<String, Integer> MOVE_COSTS = Map.of(
            "up", 1,
            "down", 1,
            "left", 1,
            "right", 1
    );

    public List<String> solve(State2 gameState) {
        Set<String> visitedStates = new HashSet<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));

        priorityQueue.add(new Node(gameState, new ArrayList<>(), 0));
        visitedStates.add(encodeState(gameState.board));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            State2 currentState = currentNode.state;
            List<String> currentPath = currentNode.path;
            int currentCost = currentNode.cost;

            if (currentState.checkGoal(currentState.board)) {
                return currentPath;
            }

            for (String direction : DIRECTIONS) {
                State2 newState = cloneState(currentState);
                newState.move(direction);

                int moveCost = MOVE_COSTS.getOrDefault(direction, Integer.MAX_VALUE);

                String encodedState = encodeState(newState.board);
                if (!visitedStates.contains(encodedState)) {
                    visitedStates.add(encodedState);
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(direction);
                    priorityQueue.add(new Node(newState, newPath, currentCost + moveCost));
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
        int cost;

        Node(State2 state, List<String> path, int cost) {
            this.state = state;
            this.path = path;
            this.cost = cost;
        }
    }
}
