package algorithm.classification.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by bbking on 16-12-25.
 */
public class Exchange {

    public static void swap(Object [] arr,int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
