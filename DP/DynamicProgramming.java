package DP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DynamicProgramming {

    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        //store max so far in left and right array
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            if (waterLevel >= height[i]) ans += waterLevel - height[i];
        }
        return ans;
    }
    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{i, 0, heightMap[i][0]});
            pq.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = visited[i][n - 1] = true;
        }
        for (int j = 0; j < m; j++) {
            pq.offer(new int[]{0, j, heightMap[0][j]});
            pq.offer(new int[]{m - 1, j, heightMap[m- 1][j]});
            visited[0][j] = visited[m - 1][j] = true;
        }
        int water = 0;
        int maxBoundary = 0;
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int x = cell[0], y = cell[1], h = cell[2];
            maxBoundary = Math.max(maxBoundary, h);
            for (int[] dir : DIRS) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (heightMap[nx][ny] < maxBoundary) {
                        water += maxBoundary - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx, ny, Math.max(heightMap[nx][ny], maxBoundary)});
                }
            }
        }
        return water;
    }

    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int[][] dp = new int[n][3];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return findEvents(events, 0, 0, n, dp);
    }

    private int findEvents(int[][] events, int idx, int cnt, int n, int[][] dp) {
        if (cnt == 2 || idx >= n) return 0;
        if (dp[idx][cnt] != -1) return dp[idx][cnt];

        int end = events[idx][1];
        int low = idx + 1, high = n - 1;
        while (low < high) {
            int mid = low + (high - low)/2;

            if (events[mid][0] > end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        int include = events[idx][2] + ((low < n && events[low][0] > end)
                ? findEvents(events, low, cnt + 1, n, dp)
                : 0);
        int exclude = findEvents(events, idx + 1, cnt, n, dp);
        dp[idx][cnt] = Math.max(include, exclude);
        return dp[idx][cnt];
    }

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Number of events " + dp.maxTwoEvents(new int[][]{{1,3,2},{4,5,2},{2,4,3}})); // Output: 6
       // System.out.println("Trapped water: " + dp.trap(height1)); // Output: 6

      //  int[] height2 = {4,2,0,3,2,5};
      //  System.out.println("Trapped water: " + dp.trap(height2)); // Output: 9
    }
}
