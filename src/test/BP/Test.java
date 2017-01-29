package BP;

import com.aimdb.container.BP.BPTree;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by bbking on 17-1-16.
 */
public class Test {
    private  int j;
    public static void main(String[] args) {

        BPTree<String, Integer> bt = new BPTree<String, Integer>();
        for (int i=0;i<10;i++){
            bt.put("k"+i,i);
        }
        System.out.println(JSONObject.toJSON(bt.get("k0")));



       /*BPTree<String, Integer> bt = new BPTree<String, Integer>();
       BaseAimDBTemplate baseAimDBTemplate = new BaseAimDBTemplate();
        for(int i=0;i<1000000;i++){
            User user = new User("num"+i,"bbking"+i,"徐刚文"+i, i);
            try {
                baseAimDBTemplate.insert(user,user.getId(),bt);
            } catch (InsertException e) {
                e.printStackTrace();
            }
        }
        DBUtils.writeObj2File("btree.obj",bt);   */

        /*********************
         * 数据获取        百万
         * *********************/
        /*long start = System.currentTimeMillis();
        BaseAimDBTemplate btl  = new BaseAimDBTemplate();
        User u =new User();
        BPTree<String, Integer> obt = ( BPTree<String, Integer> )DBUtils.getObjFromFile("btree.obj");
        try {
            long middle = System.currentTimeMillis();
            System.out.println("1:"+(middle-start));
            int temp = obt.get("num999999").intValue();
            long searchIndex = System.currentTimeMillis();
            System.out.println("2:"+(searchIndex-middle));
            btl.selectBySeek(u,temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSON(u));
        System.out.println(System.currentTimeMillis()-start);*/


    }
}
