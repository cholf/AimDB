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
    public static void main(String[] args) {

        BaseAimDBTemplate baseAimDBTemplate = new BaseAimDBTemplate();
        BBTree<String, Integer> bt = new BBTree<String, Integer>();
        for(int i=0;i<100;i++){
            User user = new User("1"+i,"xuwsldjf"+i,"刚文"+i, DBUtils.field_size);
            try {
                baseAimDBTemplate.insert(user);
            } catch (InsertException e) {
                e.printStackTrace();
            }
            //索引
            bt.put(user.getId(),i>0?30*i:30);
        }
        DBUtils.writeObj2File("btree.obj",bt);


        /*********************
         * 数据获取
         * *********************/
       /* BaseAimDBTemplate btl  = new BaseAimDBTemplate();
        User u =new User();
        BBTree<String, Integer> obt = ( BBTree<String, Integer> )DBUtils.getObjFromFile("btree.obj");
        try {
            btl.selectBySeek(u,DBUtils.field_size,obt.get("197").intValue());
        } catch (SeekException e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSON(u));*/



    }
}
