package week169;

import java.util.LinkedList;

/**
 * 跳跃游戏 III
 * BFS
 * @author: 胖虎
 * @date: 2019/12/29 11:00
 **/
public class CanReach {
    public boolean canReach(int[] arr, int start) {
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        if (arr[start] == 0) {
            return true;
        }
        list.add(start);
        return bfs(visited, arr, list);
    }

    private boolean bfs(boolean[] visited, int[] arr, LinkedList<Integer> list) {
        if (list.size() == 0) {
            return false;
        }
        int index = list.pollFirst();
        int range = arr[index];
        if (visited[index]) {
            return bfs(visited, arr, list);
        }
        if (range == 0) {
            return true;
        }

        visited[index] = true;


        if (index + range < arr.length) {
            list.add(index + range);
        }
        if (index - range >= 0) {
            list.add(index - range);
        }


        return bfs(visited, arr, list);
    }

    public static void main(String[] args) {
        CanReach c = new CanReach();
        int[] arr = {4,2,3,0,3,1,2};
        System.out.println(c.canReach(arr, 5));
    }
}
