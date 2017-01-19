package common;

import com.aimdb.begin.SimpleDBFile;
import com.aimdb.model.User;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by bbking on
 */
public class TestSi {
    public static void main(String[] args) {
        SimpleDBFile s = new SimpleDBFile();
        s.insert(s.generateUsers(10));
        try {
            List<User> us= s.select();
            for (User u :us){
                System.out.println(JSONObject.toJSON(u));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
