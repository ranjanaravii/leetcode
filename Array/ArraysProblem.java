package Array;

import java.util.*;

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

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        Map<Integer, Integer> mp = new HashMap<>();
        int n = citations.length;
        for (int i = 0; i < n/2; i++) {
            int temp = citations[i];
            citations[i] = citations[n - i - 1];
            citations[n - i - 1] = temp;
        }
        int hIndex = citations[0];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= i + 1) {
                hIndex = i + 1;
            }
        }
        return hIndex;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int totalFuel = 0;
            int stop = 0; int j = i;
            while (stop < n) {
                totalFuel += gas[j % n] - cost[j % n];
                stop += 1;
                j += 1;
            }
            if (stop == n && totalFuel >= 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        ArraysProblem arraysProblem = new ArraysProblem();
        System.out.println(arraysProblem.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
       // System.out.println(arraysProblem.majorityElement(new int[]{3,2,3}));
    }
}
