/**
* @author liji
* @date 2021/12/4
 * */

public class ConstructBinaryTreeFromPreorderAndInorderTraversal105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int start1, int end1, int[] inorder, int start2, int end2) {
        if (start1 > end1 || start2 > end2) return null;
        TreeNode root = new TreeNode(preorder[start1]);
        int rootIndex = findRootIndexInInorder(root.val, inorder, start2, end2);
        //左子树长度
        int leftLength = rootIndex - start2;
        TreeNode leftNode = buildTree(preorder, start1 + 1, start1 + leftLength, inorder, start2, rootIndex - 1);
        TreeNode rightNode = buildTree(preorder, start1 + leftLength + 1, end1, inorder, rootIndex + 1, end2);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    private int findRootIndexInInorder(int root, int[] inorder, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == root) {
                return i;
            }
        }
        return -1;
    }

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
