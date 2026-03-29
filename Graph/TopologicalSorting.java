package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSorting {


    static void findTopoSort(int node, int[] vis, List<List<Integer>> adj, Stack<Integer> st) {
        vis[node] = 1;
        for (Integer it : adj.get(node)) {
            if (vis[it] == 0) {
                findTopoSort(it, vis, adj, st);
            }
        }
        st.push(node);
    }

    static List<Integer> topoSort(List<List<Integer>> adj) {
        int n = adj.size();
        Stack<Integer> st = new Stack<>();
        int[] vis = new int[n];

        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                findTopoSort(i, vis, adj, st);
            }
        }

        List<Integer> topo = new ArrayList<>();
        while (!st.isEmpty()) {
            topo.add(st.pop());
        }
        return topo;
    }

    static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        addEdge(adj, 0, 1);
        addEdge(adj, 2, 1);
        addEdge(adj, 3, 2);
        addEdge(adj, 4, 2);

        List<Integer> res = topoSort(adj);

        for (int vertex : res) {
            System.out.print(vertex + " ");
        }
        System.out.println();
    }
}


