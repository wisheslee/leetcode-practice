/**
 * @author liji
 * @date 2022/1/8
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        makeSet(m * n);
        //先连边
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;
                if (find(i * n + j) == (i * n + j)) {
                    System.out.println("i=" + i + ";j=" + j);

                }
                if (i + 1 < m && grid[i + 1][j] == '1') union(i * n + j, (i + 1) * n + j);
                if (j + 1 < n && grid[i][j + 1] == '1') union(i * n + j, i * n + j + 1);
            }
        }
        int ans = 0;
        //再遍历看有几个集合
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0' && find(i * n + j) == (i * n + j)) ans++;
            }
        }
        return ans;
    }

    private int[] fa;
    private void makeSet(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
    }

    private int find(int x) {
        if (fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) fa[y] = x;
    }
}
