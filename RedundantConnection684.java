/**
 * @author liji
 * @date 2022/1/8
 */
public class RedundantConnection684 {
    public int[] findRedundantConnection(int[][] edges) {
        // 使用并查集实现
        // 先makeSet, 来一对边，对两个元素执行find操作，如果father是同一个，说明成环。
        int n = edges.length;
        makeSet(n + 1);
        int[] ans = null;
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            if (find(edge[0]) == find(edge[1])) {
                ans = edge;
            }
            else {
                unionSet(edge[0], edge[1]);
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

    private void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) fa[x] = y;
    }
}
