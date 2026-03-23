package Graph;

import java.util.*;

public class AdjacencyList<T> {
    private Map<T, List<T>> adjacency;

    public AdjacencyList(Map<T, List<T>> adjacency) {
        this.adjacency = adjacency;
    }
    public void addEdges(T u, T v, boolean isDirected) {
        adjacency.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        if (!isDirected) {
            adjacency.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
    }
    public void printGraph() {
        if (adjacency.isEmpty()) {
            System.out.println("Graph is empty !!");
        }
        for (Map.Entry<T, List<T>> entry : adjacency.entrySet()) {
            System.out.print(entry.getKey() + "-->");
            for (int i = 0; i < entry.getValue().size(); i++ ) {
                System.out.print( " "+entry.getValue().get(i) + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        AdjacencyList<Integer> adjacencyList = new AdjacencyList<Integer>(map);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter n :");
        int n = scanner.nextInt();
        System.out.println("Enter m :");
        int m = scanner.nextInt();

        for (int i = 0 ; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adjacencyList.addEdges(u, v, false);
        }
        adjacencyList.printGraph();
    }
}
