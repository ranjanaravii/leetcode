package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

    List<List<Integer>> res = new ArrayList<>();

    void backtrack(int start, int n, int k, List<Integer> temp) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(i + 1, n, k, temp);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * Combinations:
     * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

        You may return the answer in any order.

        Example 1:

        Input: n = 4, k = 2
        Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
        Explanation: There are 4 choose 2 = 6 total combinations.
        Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
        Example 2:

        Input: n = 1, k = 1
        Output: [[1]]
        Explanation: There is 1 choose 1 = 1 total combination.
     * @param n
     * @param k
     * @return
     */

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k, new ArrayList<>());
        return res;
    }


    static int CountPath(int r, int c, boolean[][] board) {
        if (r == board.length - 1 && c == board[0].length - 1) {
            return 1;
        }
        if (r == board.length || c == board[0].length) {
            return 0;
        }
        if (board[r][c] == true) {
            return 0;
        }
        board[r][c] = true;
        int count = 0;
        count += CountPath(r + 1, c, board);
        count += CountPath(r, c + 1, board);
        count += CountPath(r - 1, c, board);
        count += CountPath(r, c - 1, board);
        board[r][c] = false;
        return count;
    }


    public static void main(String[] args) {
        boolean[][] board = new boolean[4][4];
        System.out.println(CountPath(0, 0, board));
    }

}
