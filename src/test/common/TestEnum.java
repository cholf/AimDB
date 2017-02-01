package common;

import com.aimdb.utils.DBUtils;

/**
 * Created by bbking on 17-1-17.
 */
public class TestEnum {

    public static void main(String[] args) {

       /* TM tm = new  TM();
        tm.setEm(TT.BACK_WAY);
        DBUtils.writeObj2File("tm,obj",tm);*/

        TM tm =(TM)DBUtils.getObjFromFile("tm,obj");
       System.out.print(tm.getEm().equals(TT.BACK_WAY));

    }
}
