package week172;

import CommonClass.TreeNode;

/**
 * @author: 胖虎
 * @date: 2020/1/19 11:29
 **/
public class RemoveLeafNodes {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        TreeNode lChild = root.left;
        TreeNode rChild = root.right;
        if (lChild != null &&
                (lChild.left != null || lChild.right != null)) {
            removeLeafNodes(root.left, target);
        }
        if (rChild != null &&
                (rChild.left != null || rChild.right != null)) {
            removeLeafNodes(root.right, target);
        }
        if (lChild != null &&
                (lChild.left == null && lChild.right == null)
                && target == lChild.val) {
            root.left = null;
        }
        if (rChild != null &&
                (rChild.left == null && rChild.right == null)
                && target == rChild.val) {
            root.right = null;
        }

        if (root.left == null && root.right == null && root.val == target){
            return null;
        }
        return root;
    }
}
