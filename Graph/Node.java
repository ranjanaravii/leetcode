package Graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int key, value;
    Node next, prev;
    public List<Node> neighbors;
    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
    public Node() {
        value = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        value = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        value = _val;
        neighbors = _neighbors;
    }
}
