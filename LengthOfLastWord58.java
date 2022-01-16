/**
 * @author liji
 * @date 2022/1/16
 */
public class LengthOfLastWord58 {
    public int lengthOfLastWord(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (res == 0 && c == ' ') continue;
            if (res != 0 && c == ' ') break;
            res++;
        }
        return res;
    }
}
