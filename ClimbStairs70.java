import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 时间: O(N)
 * 空间: O(N)
 *
 * @author liji
 * @date 2021/12/26
 */
public class ClimbStairs70 {
    public int climbStairs(int n) {
        if (n == 1 || n == 2) return n;
        Integer first = cache.get(n - 1);
        if (first == null) {
            first = climbStairs(n - 1);
            cache.put(n - 1, first);
        }
        Integer second = cache.get(n - 2);
        if (second == null) {
            second = climbStairs(n - 2);
            cache.put(n - 2, first);
        }
        return first + second;
    }

    private Map<Integer, Integer> cache = new ConcurrentHashMap<>();
}
