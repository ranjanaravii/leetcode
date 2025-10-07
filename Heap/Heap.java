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

    public ListNode mergeKLists2(ListNode[] lists) {
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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeSort(lists, 0, lists.length - 1);
    }

    private ListNode mergeSort(ListNode[] lists, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists[left];
        }

        int mid = left + (right - left) / 2;
        ListNode leftNode = mergeSort(lists, left, mid);
        ListNode rightNode = mergeSort(lists, mid + 1, right);
        return mergeLinkedList(leftNode, rightNode);
    }

    private ListNode mergeLinkedList(ListNode leftNode, ListNode rightNode) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (leftNode != null && rightNode != null) {
            if (leftNode.val <= rightNode.val) {
                current.next = leftNode;
                leftNode = leftNode.next;
            } else {
                current.next = rightNode;
                rightNode = rightNode.next;
            }
            current = current.next;
        }

        if (leftNode != null) {
            current.next = leftNode;
        } else {
            current.next = rightNode;
        }

        return dummy.next;
    }


    public List<Integer> mergeKLists(List<List<Integer>> lists) {
        if (lists == null || lists.isEmpty()) {
            return new ArrayList<>();
        }

        boolean hasNonEmpty = lists.stream().anyMatch(lst -> lst != null && !lst.isEmpty());
        if (!hasNonEmpty) {
            return new ArrayList<>();
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null && !lists.get(i).isEmpty()) {
                heap.offer(new int[]{lists.get(i).get(0), i, 0});
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int value = current[0], listIdx = current[1], elemIdx = current[2];
            result.add(value);

            if (elemIdx + 1 < lists.get(listIdx).size()) {
                int nextValue = lists.get(listIdx).get(elemIdx + 1);
                heap.offer(new int[]{nextValue, listIdx, elemIdx + 1});
            }
        }

        return result;
    }

    public int nthUglyNumber(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();

        minHeap.offer(1L);
        seen.add(1L);

        int[] factors = {2, 3, 5};
        long ugly = 1;

        for (int i = 0; i < n; i++) {
            ugly = minHeap.poll();

            for (int factor : factors) {
                long newUgly = ugly * factor;
                if (!seen.contains(newUgly)) {
                    minHeap.offer(newUgly);
                    seen.add(newUgly);
                }
            }
        }

        return (int) ugly;
    }

    public int maxProduct(int[] nums) {
        int[] sortedDesc = Arrays.stream(nums)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        return ((sortedDesc[0] - 1) * (sortedDesc[1] - 1));

//        Arrays.sort(nums);
//        int x = nums[nums.length - 1];
//        int y = nums[nums.length - 2];
//        return (x - 1) * (y - 1);
    }

    public int maxProduct2(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
        }
        int x = maxHeap.poll();
        int y = maxHeap.poll();
        return (x - 1) * (y - 1);
    }

    public int[] maxSubsequence(int[] nums, int k) {
        int[][] temp = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = new int[]{nums[i], i};
        }
        Arrays.sort(temp, (a, b) -> b[0] - a[0]);
        Arrays.sort(temp, 0, k, (a, b) -> a[1] - b[1]);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = temp[i][0];
        }
        return res;
    }

    public int[] maxSubsequence2(int[] nums, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]);

        for(int i = 0; i < nums.length; i++) {
            minHeap.offer(new int[]{nums[i], i});
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<int[]> topKElement = new ArrayList<>(minHeap);
        topKElement.sort((a, b) -> a[1] - b[1]);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topKElement.get(i)[0];
        }

        return result;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        int k = 2;
        System.out.println(heap.findKthLargest(nums, k));
    }

}
