/**
 * @author liji
 * @date 2021/12/18
 */
public class KokoEatingBananas875 {
    public int minEatingSpeed(int[] piles, int h) {
        //因为h >= piles.length, 所以以堆最小值为最小，所有堆子和为最大
        long left = 1;
        long right = 0;
        for (int pile : piles) {
            right += pile;
        }
        while (left < right) {
            long mid = (left + right) / 2;
            if (canEat(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int)right;
    }

    private boolean canEat(int[] piles, int h, long k) {
        for (int j = 0; j < piles.length; j++) {
            long num = (piles[j] % k == 0 ? piles[j] / k : (piles[j] / k) + 1);
            h -= num;
        }
        if (h < 0) return false;
        else return true;
    }
}
