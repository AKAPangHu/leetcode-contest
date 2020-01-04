package week169;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 两棵二叉搜索树中的所有元素
 * 直接排序------显然不是最优解
 * 时间复杂度O(NlogN)
 * 空间复杂度O(N)
 *
 * @author: 胖虎
 * @date: 2019/12/29 10:47
 **/
public class AllElements {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root1 != null) {
            dfs(root1, arr);
        }
        if (root2 != null) {
            dfs(root2, arr);
        }

        arr.sort(Comparator.comparingInt((Integer::intValue)));
        return arr;
    }

    private void dfs(TreeNode root, ArrayList<Integer> arr) {
        if (root.left != null) {
            dfs(root.left, arr);
        }
        if (root.right != null) {
            dfs(root.right, arr);
        }
        arr.add(root.val);
    }
}
