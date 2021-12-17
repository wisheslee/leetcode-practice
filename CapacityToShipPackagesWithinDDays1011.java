/**
 * @author liji
 * @date 2021/12/18
 */
public class CapacityToShipPackagesWithinDDays1011 {
    public int shipWithinDays(int[] weights, int days) {
        //把最大的包裹重量作为最小值，重量和作为最大值
        //从最小到最大试一下，形成0 0 0 1 1 1的单调队列，可使用二分答案
        int left = 0;
        int right =  0;
        for (int weight : weights) {
            left = Math.max(weight, left);
            right += weight;
        }
        while (left < right) {
            System.out.println("left=" + left + ";right=" + right);
            int mid = (left + right) / 2;
            if (valid(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println("final" + right);
        return right;
    }

    private boolean valid(int[] weights, int days, int mid) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            int next = sum + weights[i];
            if (next > mid) {
                days--;
                sum = weights[i];
            } else {
                sum = next;
            }
        }
        days--;
        return days >= 0;
    }
}
