package common;

import com.aimdb.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbking on 17-1-17.
 */
public class TestList {
    public static void main(String[] args) {


        List<User> list = new ArrayList<User>();
        for (int i=0;i<10;i++){
            User u = new User();
            u.setName("123");
            if(list.contains(u)){
                System.out.print(true);
            }else {
                list.add(u);
            }
        }
    }
}
