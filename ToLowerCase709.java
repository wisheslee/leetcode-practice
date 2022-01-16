/**
 * @author liji
 * @date 2022/1/16
 */
public class ToLowerCase709 {
    class Solution {
        public String toLowerCase(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ('a' <= c && c <= 'z')  res += c;
                else res = res + (char)(c + 'a' - 'A');
            }
            return res;
        }
    }
}
