package B;

import com.aimdb.container.BP.BPTree;

/**
 * Created by bbking
 */
public class BTreeTest {

    public static void main(String[] args) {

        BPTree<String, String> st = new BPTree<String, String>();
        st.put("9", "9久");
        st.put("2", "2二");
        st.put("3", "3三");
        st.put("4", "4四");
        st.put("5", "5五");
        st.put("6", "6六");

        System.out.println("9:" + st.get("9"));
        System.out.println("2:" + st.get("2"));
        System.out.println("3:" + st.get("3"));
        System.out.println("4: " + st.get("4"));
        System.out.println("5: " + st.get("5"));
        System.out.println("6: " + st.get("6"));

    }
}
