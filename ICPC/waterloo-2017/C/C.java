import java.io.*;
import java.util.*;

public class C {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    int[] nums = new int[n];
    for (int i = 0; i < n; i++)
      nums[i] = in.nextInt();
    Arrays.sort(nums);
    List<Integer> diffs = new ArrayList<>();
    HashSet<Integer> uDiffs = new HashSet<>();
    for (int i = 0; i <= n-k; i++) {
      int x = nums[i+k-1] - nums[i];
      if (!uDiffs.contains(x)) {
        uDiffs.add(x);
        diffs.add(x);
      }
    }
    Collections.sort(diffs);
    // System.out.println(min + " " + max);
    System.out.println(binarySearch(nums, diffs, 0, diffs.size() - 1, n, k));
  }
  public static int binarySearch(int[] nums, List<Integer> diffs, int min, int max, int n, int k) {
    int left = min;
    int right = max;
    while (true) {
      if (left == right) {
        return diffs.get(left);
      }
      int mid = (left + right) / 2;
      if (works(nums, diffs.get(mid), n, k)) {
        right = mid;
      }
      else {
        left = mid + 1;
      }
    }
  }
  public static boolean works(int[] nums, int margin, int n, int k) {
    if (margin < nums[k-1] - nums[0]) {
      return false;
    }
    int high = k-1;
    for (int i = 1; i <= n-k; i++) {
      if (i > high + 1) {
        return false;
      }
      if (nums[i+k-1] - nums[i] <= margin) {
        high = Math.max(high, i+k-1);
      }
    }
    return (high == nums.length-1);
  }
}