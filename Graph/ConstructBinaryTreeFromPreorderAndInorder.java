package Graph;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorder {

        private int preIndex = 0;
        private Map<Integer, Integer> inorderMap = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {

            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }

            return build(preorder, 0, inorder.length - 1);
        }

        private TreeNode build(int[] preorder, int left, int right) {

            if (left > right) {
                return null;
            }

            int rootVal = preorder[preIndex++];
            TreeNode root = new TreeNode(rootVal);

            int mid = inorderMap.get(rootVal);

            root.left = build(preorder, left, mid - 1);
            root.right = build(preorder, mid + 1, right);

            return root;
        }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorder s = new ConstructBinaryTreeFromPreorderAndInorder();
        System.out.println(s.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
    }
}
