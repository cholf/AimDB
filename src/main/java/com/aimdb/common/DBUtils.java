package com.aimdb.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by bbking on 16-12-26.
 */
public class DBUtils {

    public static final int field_size = 10;
    private  static  final  String db_name="TestInterceptDB.data";
    private  static  RandomAccessFile randomAccessFile = null;

    public  static RandomAccessFile getRandomAccessFile(){
        if(randomAccessFile == null){
            try {
                randomAccessFile = new RandomAccessFile(new File(db_name),"rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return  randomAccessFile;
    }

    public  static  void closeRandomAccessFile(){
        if(randomAccessFile != null){
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
