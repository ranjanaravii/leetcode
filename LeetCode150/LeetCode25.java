package LeetCode150;

import java.util.*;


//3SUM
public class LeetCode25 {

    public List<List<Integer>> threeSum(int[] nums) {
       if (nums.length < 3) return new ArrayList<>();
       Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

                }
            }
        }
        return new ArrayList<>(ans);
    }

    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int cnt = 1;
        int maxCnt = 0;
        if (nums.length == 1) return 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] + 1 == nums[i]) {
                cnt += 1;
            } else if (nums[i - 1] == nums[i]) {
                continue;
            } else {
                cnt = 1;
            }
            maxCnt = Math.max(maxCnt, cnt);
        }
        return maxCnt;
    }
}
