package com.aimdb.core;

import com.aimdb.common.DBUtils;
import com.aimdb.exception.InsertException;
import com.aimdb.exception.SeekException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bbking on 16-12-26.
 */
public class BaseAimDBTemplate {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 15, 200,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

    public void insert(Object object) throws InsertException {
        try {
            RandomAccessFile raf = DBUtils.getRandomAccessFile();
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
                    fields[i].setAccessible(true);
                    raf.write((fields[i].get(object)).toString().getBytes());
                }
            }
        } catch (Exception e) {
            logger.error("BaseAimDBTemplate-insert,e:{}", e);
            throw new InsertException("BaseAimDBTemplate-insert-Exception");
        } finally {
          //  DBUtils.closeRandomAccessFile();
        }
    }

    public void update() {

    }

    public void select() {

    }

    public void selectOne(Object object,int size) {
        byte [] buffer = new byte[size];
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                DBUtils.getRandomAccessFile().read(buffer);
                fields[i].set(object,new String(buffer).trim());
            } catch (Exception e) {
                logger.error("BaseAimDBTemplate-selectOne,e:{}",e);
            }
        }
        System.out.println(object.toString());
    }

    /**
     * 根据偏移量索引查询数据
     * @param object
     * @param size
     * @param seek
     */
    public void selectBySeek(Object object, int size, int seek) throws SeekException {
        byte[] buffer = new byte[size];
        RandomAccessFile rf = null;
        try {
            rf = DBUtils.getRandomAccessFile();
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                seek = i > 0 ? (seek + size) : seek;
                rf.seek(seek);
                rf.read(buffer);
                fields[i].set(object, new String(buffer).trim());
            }
        } catch (Exception e) {
            logger.error("BaseAimDBTemplate-selectBySeek,e:{}", e);
            throw new SeekException("BaseAimDBTemplate-selectBySeek-Exception");
        }finally {
           // DBUtils.closeRandomAccessFile();
        }
        System.out.println(object.toString());
    }
}
