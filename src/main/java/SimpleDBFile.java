import com.aimdb.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbking on 16-12-17.
 */
public class SimpleDBFile {

    //
    public List generateUsers(int size) {
        List users = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            User user = new User();
            user.setId(i + 1+"");
            user.setName("测试用户：" + i);
            user.setPhoneNum("185112862" + i);
            users.add(user);
        }
        return users;
    }

    // 入库
    private String fileName = "aimdb.data";

    public void insert(List<User> users) {
        String data = "";
        for (User u : users) {
            data += toDbString(u) + "\r\n";
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toDbString(User user) {
        return user.getId() + "/#" + user.getName() + "/#" + user.getPhoneNum();
    }

    private User toUserByString(String userStr) {
        String users[] = userStr.split("/#");
        User u = new User(users[0], users[1], users[2],22);
        return u;
    }

    // 查询
    public List<User> select() throws IOException {
        List<User> users = new ArrayList<User>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String dataLine = "";
        while ((dataLine = bufferedReader.readLine()) != null) {
            users.add(toUserByString(dataLine));

        }
        return users;
    }

}
