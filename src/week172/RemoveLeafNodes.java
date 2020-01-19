package week172;

import CommonClass.TreeNode;

/**
 * 删除给定值的叶子节点
 * 递归+DFS
 * @author: 胖虎
 * @date: 2020/1/19 11:29
 **/
public class RemoveLeafNodes {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.left == null && root.right == null && root.val == target) {
            //虽然传递的对象参数是指针的副本，但是由于返回值的赋值原因，没问题
            root = null;
        }
        return root;
    }
}
