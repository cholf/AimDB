import com.aimdb.common.DBUtils;
import com.aimdb.core.BaseTemplate;
import com.aimdb.model.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by bbking on 16-12-17.
 */
public class Test {

    public static void main(String[] args) throws IOException {

      /*  SimpleDBFile simpleDBFile = new SimpleDBFile();

        //simpleDBFile.insert(simpleDBFile.generateUsers(7));
        List<User>  users= simpleDBFile.select();
        for (User u:users){
            System.out.print(u.getId()+u.getName()+u.getPhoneNum());
        }
*/
       /* User user = new User("12345","xuwsldjf","徐刚问", DBUtils.field_size);
        BaseTemplate baseTemplate  = new BaseTemplate();
        baseTemplate.insert(user);*/

        BaseTemplate baseTemplate  = new BaseTemplate();
        User user = new User();
        baseTemplate.selectOne(user,10);
        System.out.println(user.toString());

    }
}
