package com.aimdb.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

/**
 * Created by bbking on 16-12-26.
 */
public class DBUtils {

    public static final int field_size = 10;
    private  static  final  String db_name="TestInterceptDB.data";
    private  static  RandomAccessFile randomAccessFile = null;

    public  static RandomAccessFile getRandomAccessFile() throws FileNotFoundException {
        if(randomAccessFile == null){
            try {
                randomAccessFile = new RandomAccessFile(new File(db_name),"rw");
            } catch (FileNotFoundException e) {
               throw new  FileNotFoundException(e.getMessage());
            }
        }
        return  randomAccessFile;
    }

    public  static  void closeRandomAccessFile(){
        if(randomAccessFile != null){
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                new IOException(e.getMessage());
            }
        }
    }

    public static void writeObj2File(String fName, Object obj) {
        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(fName));
            oo.writeObject(obj);
            oo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getObjFromFile(String fName) {
        try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fName));
            return oi.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
