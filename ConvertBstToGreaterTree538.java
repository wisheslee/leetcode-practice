import java.util.ArrayList;
import java.util.List;

/**
 * @author liji
 * @date 2021/12/12
 */
public class ConvertBstToGreaterTree538 {
    /**
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        inOderList = new ArrayList<>();
        suffixSum = new ArrayList<>();
        //先中序遍历得到有序数组
        inOrder(root);
        //求后缀和
        suffixSum(inOderList);
        return root;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        inOderList.add(root);
        inOrder(root.right);
    }

    private void suffixSum(List<TreeNode> inOderList) {
        int sum = 0;
        for (int i = inOderList.size() - 1; i >= 0; i--) {
            sum += inOderList.get(i).val;
            inOderList.get(i).val = sum;
        }
    }

    List<TreeNode> inOderList;
    List<Integer> suffixSum;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
