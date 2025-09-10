package Heap;

import java.util.*;

public class Heap {

    /***************************************************************************************************************/
    public List<Integer> findClosestElements(int[] nums, int k, int target) {
        int left = 0;
        int right = nums.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target - nums[mid] > nums[mid + k] - target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(nums[i]);
        }
        return result;
    }

    /***************************************************************************************************************/
    /**
     * Top-K Largest Elements in an Array
     * Heaps are useful for solving problems that require finding the "top k"
     * elements in an array
     * Given an integer array nums, return the 3 largest elements in the array in
     * any order.
     * Input: nums = [9, 3, 7, 1, -2, 6, 8]
     * Output: [8, 7, 9]
     */
    public int[] topKLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            pq.offer(num);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    /***************************************************************************************************************/

    /**
     * K Closest Points to the Origin
     * Given an array of points where points[i] = [xi, yi] represents a point on
     * the X-Y plane and an integer k, return the k closest points to the origin
     * (0, 0).
     * Input: points = [[1,3],[-2,2]], k = 1
     * Output: [[-2,2]]
     * 
     * @param points
     * @param k
     * @return
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(a -> a[0]));

        for (int[] p : points) {
            int dist = p[0] * p[0] + p[1] * p[1];
            minHeap.offer(new int[] { dist, p[0], p[1] });
        }
        int[][] res = new int[k][2];

        for (int i = 0; i < k; i++) {
            int[] p = minHeap.poll();
            res[i] = new int[] { p[1], p[2] };
        }
        return res;
    }

    public int[][] kClosest2nd(int[][] points, int k) {

        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int[] point : points) {
            int x = point[0], y = point[1];
            int distance = x * x + y * y;
            map.putIfAbsent(distance, new ArrayList<>());
            map.get(distance).add(point);
        }

        int[][] result = new int[k][2];
        int index = 0;
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            for (int[] point : entry.getValue()) {
                if (index == k)
                    return result;
                result[index][0] = point[0];
                result[index][1] = point[1];
                index++;
            }
        }
        return result;
    }

    /***************************************************************************************************************/

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            pq.offer(num);
        }
        while (k > 1 && !pq.isEmpty()) {
            pq.poll();
            k -= 1;
        }
        return pq.peek();
    }

    /**
     * This way, you maintain the k largest elements seen so far,
     * and the smallest element in this heap is the k-th largest.
     * This reduces space complexity from O(n) to O(k) and can improve performance
     * for large arrays.
     * 
     * @param nums
     * @param k
     * @return
     */
    public int kthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        if (nums.length == 0) {
            return -1;
        }
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        return minHeap.peek();
    }

    /**
     *
     * @param nums
     * @return
     */

    public String[] findRelativeRanks(int[] nums) {
        int[][] pair = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }

        Arrays.sort(pair, (a, b) -> (b[0] - a[0]));

        String[] result = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                result[pair[i][1]] = "Gold Medal";
            }
            else if (i == 1) {
                result[pair[i][1]] = "Silver Medal";
            }
            else if (i == 2) {
                result[pair[i][1]] = "Bronze Medal";
            }
            else {
                result[pair[i][1]] = (i + 1) + "";
            }
        }

        return result;
    }

    public String[] findRelativeRanksPriorityQueue(int[] nums) {
        int n = nums.length;
        String[] result = new String[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        int rank = 1;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int idx = top[1];

            if (rank == 1) result[idx] = "Gold Medal";
            else if (rank == 2) result[idx] = "Silver Medal";
            else if (rank == 3) result[idx] = "Bronze Medal";
            else result[idx] = String.valueOf(rank);

            rank++;
        }

        return result;
    }

    /*************************************************************************************************/
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int i = 0; i < mat.length; i++) {
            int soldiers = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    soldiers += 1;
                }
            }
            heap.add(new int[]{soldiers, i});
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[1];
        }
        return res;
    }

    /*************************************************************************************************/

    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> nodes = new ArrayList<>();
        for (ListNode lst : lists) {
            while (lst != null) {
                nodes.add(lst.val);
                lst = lst.next;
            }
        }
        Collections.sort(nodes);

        ListNode res = new ListNode(0);
        ListNode cur = res;
        for (int node : nodes) {
            cur.next = new ListNode(node);
            cur = cur.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        int k = 2;
        System.out.println(heap.findKthLargest(nums, k));
    }

}
