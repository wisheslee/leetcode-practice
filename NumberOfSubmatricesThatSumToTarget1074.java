/**
 * @author liji
 * @date 2021/11/28
 */
public class NumberOfSubmatricesThatSumToTarget1074 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        //求二维的前缀和
        //递推公式 sum(i, j) = sum(i - 1, j) + sum(i, j - 1) + matrix(i, j) - sum(i - 1, j - 1);
        int x = matrix.length;
        int y = matrix[0].length;
        int[][] sum = new int[x + 1][y + 1];
        for (int i = 1; i < sum.length; i++) {
            for (int j = 1; j < sum[i].length; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] + matrix[i - 1][j - 1] - sum[i - 1][j - 1];
            }
        }
        //子矩阵公式：sum(x2, y2) - sum(x1, y2) - sum(x2, y1) + sum(x2, y2)
        //以例题为例，得到的前缀和二维数组，
        //0 0 0 0
        //0 0 1 1
        //0 1 3 4
        //0 1 4 5
        int res = 0;
        for (int x1 = 0; x1 < x + 1; x1++) {
            for (int y1 = 0; y1 < y + 1; y1++) {
                for (int x2 = x; x2 > x1; x2--) {
                    for (int y2 = y; y2 > y1; y2--) {
                        int count = sum[x2][y2] - sum[x1][y2] - sum[x2][y1] + sum[x1][y1];
                        if (count == target) res++;
                        if (Math.abs(count) < Math.abs(target)) break;
                    }
                }
            }
        }
        return res;
    }
}
