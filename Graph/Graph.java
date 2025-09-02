package Graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Graph is a collection of nodes and edges.
 * graph = nodes + edges
 *
 */
public class Graph {

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        return cloneGraphHelper(node, map);
    }

    private Node cloneGraphHelper(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node newNode = new Node(node.value);
        map.put(node, newNode);
        for (Node neighbour : node.neighbors) {
            newNode.neighbors.add(cloneGraphHelper(neighbour, map));
        }
        return newNode;
    }

}
