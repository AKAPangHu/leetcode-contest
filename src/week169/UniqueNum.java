package week169;

/**
 * 取巧方法，时间空间均不错
 * 和为零的N个唯一整数
 * @author: 胖虎
 * @date: 2019/12/29 10:32
 **/
public class UniqueNum {
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        if (n % 2 == 1){
            arr[0] = 0;
            n--;
        }
        for (int i = 0; i < n; i += 2) {
            arr[i] = -(i + 1);
            arr[i + 1] = i + 1;
        }
        return arr;
    }
}
