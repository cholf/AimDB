import com.aimdb.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bbking on 16-12-17.
 */
public class Test {

    public static void main(String[] args) throws IOException {

        SimpleDBFile simpleDBFile = new SimpleDBFile();

        //simpleDBFile.insert(simpleDBFile.generateUsers(7));
        List<User>  users= simpleDBFile.select();
        for (User u:users){
            System.out.print(u.getId()+u.getName()+u.getPhoneNum());
        }


        //
        List arraylist= new ArrayList();


    }
}
