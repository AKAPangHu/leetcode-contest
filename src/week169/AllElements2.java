package week169;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 两棵二叉搜索树中的所有元素
 * 归并排序-----由于有序性
 * 时间复杂度O(N)
 * 空间复杂度O(N)
 *
 * @author: 胖虎
 * @date: 2020/1/4 18:34
 **/
public class AllElements2 {

    public List<Integer> getAllElements(AllElements.TreeNode root1, AllElements.TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(list1, root1);
        dfs(list2, root2);
        List<Integer> result = new ArrayList<>();
        merge(list1, list2, result);
        return result;
    }

    private void merge(List<Integer> list1, List<Integer> list2, List<Integer> result) {
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int num1 = list1.get(i);
            int num2 = list2.get(j);
            if (num1 < num2){
                result.add(num1);
                i++;
            }
            else{
                result.add(num2);
                j++;
            }
        }
        while (i < list1.size()){
            result.add(list1.get(i++));
        }
        while (j < list2.size()){
            result.add(list2.get(j++));
        }
    }

    private void dfs(List<Integer> list, AllElements.TreeNode root) {
        if (root == null){
            return;
        }
        dfs(list, root.left);
        list.add(root.val);
        dfs(list, root.right);

    }
}
