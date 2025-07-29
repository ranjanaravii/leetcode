package TwoPointer;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // In case k > n
        reverse(nums, 0, n - 1); // Reverse the whole array
        reverse(nums, 0, k - 1); // Reverse first k elements
        reverse(nums, k, n - 1); // Reverse the remaining elements
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        int k = 3;
        rotateArray.rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
