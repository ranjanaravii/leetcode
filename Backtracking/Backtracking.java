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
