package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        int k = 2;
        System.out.println(heap.findKthLargest(nums, k));
    }

}
