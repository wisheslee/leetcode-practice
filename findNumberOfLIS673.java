/**
 * 时间O(N^2)
 * 空间O(N^2)
 *
 * @author liji
 * @date 2021/12/26
 */
public class findNumberOfLIS673 {
    public int findNumberOfLIS(int[] nums) {
        //f(n)代表以num[n]结尾的最长子序列有几位，有几个。最后再把f(n)扫一遍，设最长为n，那么最长为n的一共有几个
        //f(n) = max(f(i)) + 1, i < n && nums[i] < nums[n]
        int n = nums.length;
        int[][] dp = new int[n][2];
        int max = 0, count = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = new int[]{1, 1};
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    } else if (dp[j][0] + 1 == dp[i][0]) {
                        dp[i][1] = dp[i][1] + dp[j][1];
                    }
                }
            }
            if (dp[i][0] > max) {
                max = dp[i][0];
                count = dp[i][1];
            } else if (dp[i][0] == max) {
                count += dp[i][1];
            }
        }
        return count;
    }
}
