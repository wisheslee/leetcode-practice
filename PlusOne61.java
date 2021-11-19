public class PlusOne61 {
    public int[] plusOne(int[] digits) {
        int n = digits.length -1;
        while (n >=0 && digits[n] == 9) {
            digits[n] = 0;
            n--;
        }
        //说明是999这样数，需要整体进一位
        if (n < 0) {
            int[] result =  new int[digits.length + 1];
            //剩下都默认是0
            result[0] = 1;
            return result;
        }
        digits[n]++;
        return digits;
    }
}