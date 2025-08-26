package Array;

public class ArraysProblem {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0)
            return new int[0];
        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = mat[row][col];
            if ((row + col) % 2 == 0) { // moving up
                if (col == n - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else { // moving down
                if (row == m - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }

    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxArea = 0;
        double maxDiagonal = 0.0;

        for (int[] rect : dimensions) {
            int length = rect[0];
            int width = rect[1];

            double diagonal = Math.sqrt(length * length + width * width);
            int area = length * width;

            if (diagonal > maxDiagonal) {
                maxDiagonal = diagonal;
                maxArea = area;
            } else if (diagonal == maxDiagonal && area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }
}
