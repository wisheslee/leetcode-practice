import java.util.HashMap;
import java.util.Map;

/**
 * @author liji
 * @date 2021/11/27
 */
public class DegreeOfAnArray697 {
    public int findShortestSubArray(int[] nums) {
        // 存元素存在个数的map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.get(num) == null) map.put(num, 1);
            else map.put(num, map.get(num) + 1);
        }
        // 数组的度
        int maxCount = 0;
        // 出现最多次数的数的map，value用来存遍历中出现的次数
        Map<Integer, Integer> maxNumbers = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxNumbers = new HashMap<>();
                maxNumbers.put(entry.getKey(), 0);
            } else if (entry.getValue() == maxCount) {
                maxNumbers.put(entry.getKey(), 0);
            }
        }
        // 用来存数的索引
        Map<Integer, Integer> numberIndex = new HashMap<>();
        Integer min = 50000;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer cnt = maxNumbers.get(num);
            if (cnt != null) {
                if (cnt == 0) numberIndex.put(num, i);
                cnt++;
                maxNumbers.put(num, cnt);
                if (cnt == maxCount) min = Math.min(min, i - numberIndex.get(num) + 1);
            }
        }
        return min;
    }
}
