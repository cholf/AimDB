package common;

import com.aimdb.model.User;

/**
 * Created by bbking on 17-1-14.
 */
public class TestString {

    public static void main(String[] args) {
        for(int i = 0;i<1000000000;i++) {
            User u = new User();
            u.setName("徐刚问");
            System.out.print(u);
        }
        //System.out.print("UUU"+u.getName()==null?"":u.getName());
        //System.out.print("UUU"+((u.getName()==null)?"":u.getName()));
    }
}
