package BP;

import com.aimdb.common.DBUtils;
import com.aimdb.container.B.BBTree;
import com.aimdb.core.BaseTemplate;
import com.aimdb.model.User;

/**
 * Created by bbking on 17-1-16.
 */
public class Test {
    public static void main(String[] args) {

       /* BaseTemplate baseTemplate  = new BaseTemplate();
        BBTree<String, Integer> bt = new BBTree<String, Integer>();
        for(int i=0;i<100;i++){
            User user = new User("1"+i,"xuwsldjf"+i,"徐刚问"+i, DBUtils.field_size);
            baseTemplate.insert(user);
            //索引
            bt.put(user.getId(),i>0?30*i:30);
        }
        DBUtils.closeRandomAccessFile();
        DBUtils.writeObj2File("btree.obj",bt);*/


        /*********************
         * 数据获取
         * *********************/
        BaseTemplate btl  = new BaseTemplate();
        User u =new User();
        BBTree<String, Integer> obt = ( BBTree<String, Integer> )DBUtils.getObjFromFile("btree.obj");
        btl.selectBySeek(u,DBUtils.field_size,obt.get("199").intValue());
        System.out.println(obt);



    }
}
