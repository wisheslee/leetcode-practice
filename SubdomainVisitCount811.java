import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liji
 * @date 2021/11/27
 */
public class SubdomainVisitCount811 {
    public List<String> subdomainVisits(String[] cpdomains) {
        // 开一个map，存域名出现的次数
        Map<String, Integer> count = new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] splits = cpdomain.split(" ");
            int num = Integer.parseInt(splits[0]);
            String[] tokens = splits[1].split("\\.");
            String url = "";
            for (int i = tokens.length - 1; i >= 0; i--) {
                url = tokens[i] + (i == tokens.length - 1 ? "" : ".") + url;
                count.put(url, count.getOrDefault(url, 0) + num);
            }
        }
        List<String> res = new ArrayList<>(count.size());
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;
    }
}
