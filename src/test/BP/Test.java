package BP;

import com.aimdb.common.DBUtils;
import com.aimdb.container.B.BBTree;
import com.aimdb.core.BaseAimDBTemplate;
import com.aimdb.exception.InsertException;
import com.aimdb.exception.SeekException;
import com.aimdb.model.User;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by bbking on 17-1-16.
 */
public class Test {
    private  int j;
    public static void main(String[] args) {

       /*BBTree<String, Integer> bt = new BBTree<String, Integer>();
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
        long start = System.currentTimeMillis();
        BaseAimDBTemplate btl  = new BaseAimDBTemplate();
        User u =new User();
        BBTree<String, Integer> obt = ( BBTree<String, Integer> )DBUtils.getObjFromFile("btree.obj");
        try {
            int temp = obt.get("num999999").intValue();
            System.out.println("1:"+(System.currentTimeMillis()-start));
            btl.selectBySeek(u,temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSON(u));
        System.out.println(System.currentTimeMillis()-start);


    }
}
