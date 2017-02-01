package BP;

import com.aimdb.container.BP.BPTree;
import com.aimdb.exception.DataAccessException;
import com.aimdb.model.User;
import com.aimdb.query.AimQuery;
import com.aimdb.query.api.AimDbTemplate;
import com.aimdb.query.common.AimDbTemplateImp;
import com.aimdb.utils.DBUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by bbking on 17-1-16.
 */
public class Test {
    private  int j;
    public static void main(String[] args) {

       /* BPTree<String, String> bt = new BPTree<String, String>();
        AimDbTemplate btl  = new AimDbTemplateImp();
        for (int i=0;i<200;i++){
            User user = new User("num"+i,"bbking"+i,"徐刚文"+i, i);
            try {
                btl.insert(user);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }*/

       /* System.out.println(JSONObject.toJSON(bt.getLists("张三")));
        System.out.println(JSONObject.toJSON(bt.get("张三")));
*/


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
            btl.selectBySeek(u,temp);raf.seek(seek);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSON(u));
        System.out.println(System.currentTimeMillis()-start);*/

        AimDbTemplate btl  = new AimDbTemplateImp();
        User u =new User();
        AimQuery query = new AimQuery();
        query.appendIsCriteria("id","num0");
        try {
            btl.selectOne(u,query);
            System.out.print(JSONObject.toJSON(u));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }


    }
}
