import java.util.*;

public class AStarSolver {

    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};


    public List<String> solve(State2 gameState) {
        Set<String> visitedStates = new HashSet<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.fCost));
        int visitedNodesCount = 0;

        Node startNode = new Node(gameState, new ArrayList<>(), 0, heuristic(gameState));
        priorityQueue.add(startNode);
        visitedStates.add(encodeState(gameState.board));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            State2 currentState = currentNode.state;
            List<String> currentPath = currentNode.path;

            if (currentState.checkGoal(currentState.board)) {
                System.out.println("visitedNodesCount: " + visitedNodesCount);
                return currentPath;
            }

            for (String direction : DIRECTIONS) {
                State2 newState = cloneState(currentState);
                newState.move(direction);
                String encodedState = encodeState(newState.board);
                if (!visitedStates.contains(encodedState)) {
                    visitedStates.add(encodedState);
                    visitedNodesCount++;

                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(direction);

                    int gCost = currentNode.gCost + 1;
                    int hCost = heuristic(newState);
                    int fCost = gCost + hCost;
                    System.out.println("hhh"+hCost);
                    priorityQueue.add(new Node(newState, newPath, gCost, fCost));
                }
            }
        }

        System.out.println("visitedNodesCount: " + visitedNodesCount);

        return Collections.emptyList();
    }

    private int heuristic(State2 state) {

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

    private static class Node {
        State2 state;
        List<String> path;
        int gCost;
        int fCost;

        Node(State2 state, List<String> path, int gCost, int fCost) {
            this.state = state;
            this.path = path;
            this.gCost = gCost;
            this.fCost = fCost;
        }
    }
}
