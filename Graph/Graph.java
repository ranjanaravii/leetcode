package Graph;

import java.util.*;

/**
 * Graph is a collection of nodes and edges.
 * graph = nodes + edges
 *
 */
public class Graph {

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Map<Integer, List<Integer>> mp = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int hd = 0;
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            TreeNode node = current.first;
            hd = current.second;
            mp.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                queue.add(new Pair<>(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.add(new Pair<>(node.right, hd + 1));
            }
        }
        return new ArrayList<>(mp.values());
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        List<List<Integer>> res = verticalTraversal(root);

        for (List<Integer> temp : res) {
            System.out.print("[ ");
            for (int val : temp)
                System.out.print(val + " ");
            System.out.print("] ");
        }
    }

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

    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, root.val}); // col, row, value

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            TreeNode node = queue.poll();

            int col = curr[0];
            int row = curr[1];

            map.computeIfAbsent(col, k -> new ArrayList<>())
                    .add(new int[]{row, node.val});

            if (node.left != null) {
                q.offer(new int[]{col - 1, row + 1, node.left.val});
                queue.offer(node.left);
            }
            if (node.right != null) {
                q.offer(new int[]{col + 1, row + 1, node.right.val});
                queue.offer(node.right);
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (List<int[]> list : map.values()) {
            list.sort((a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            });

            List<Integer> colList = new ArrayList<>();
            for (int[] arr : list) colList.add(arr[1]);
            result.add(colList);
        }

        return result;
    }

}
