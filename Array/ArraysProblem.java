package Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int count = 0;
        int candidate = 0;
        Set<Integer> ans = new HashSet<>();
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            }
            if (num == candidate) {
                count += 1;
            } else {
                count -= 1;
            }
            if (count > n/3) {
                ans.add(num);
            }
        }
        return ans.stream().toList();
    }

    public static void main(String[] args) {
        ArraysProblem arraysProblem = new ArraysProblem();
        System.out.println(arraysProblem.majorityElement(new int[]{3,2,3}));
    }
}
