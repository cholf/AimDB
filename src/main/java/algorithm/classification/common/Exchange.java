package algorithm.classification.common;

/**
 * Created by bbking on 16-12-25.
 */
public class Exchange {

    /**
     * 交换函数
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}