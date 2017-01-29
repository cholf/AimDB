package com.aimdb.core;

import com.aimdb.anotation.AimField;
import com.aimdb.common.CommnUtils;
import com.aimdb.common.DBUtils;
import com.aimdb.container.BP.BPTree;
import com.aimdb.enums.BaseTypeEnum;
import com.aimdb.exception.AimFieldException;
import com.aimdb.exception.InsertException;
import com.aimdb.exception.SeekException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.util.Map;
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

    public void insert(Object object,String index,BPTree<String, Integer> bt) throws InsertException {
        try {
            RandomAccessFile raf = DBUtils.getRandomAccessFile();
            Field[] fields = object.getClass().getDeclaredFields();
            int seek = 0;
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                seek = (int) raf.getFilePointer();
                if (f.getName().equals("id")) bt.put(index, seek);
                if (f.getType().getName().equalsIgnoreCase("java.lang.String")) {
                    Map<String, Object> map = CommnUtils.interceptStr((f.get(object)).toString(), f);
                    raf.write((map.get(CommnUtils.STRING).toString()).getBytes());
                } else {
                    writeByeType(raf, CommnUtils.getBasicTypeEnum(f.getType().getName()), f.get(object));
                    System.out.println("" + f.get(object));
                }
            }
        } catch (Exception e) {
            logger.error("BaseAimDBTemplate-insert,e:{}", e);
            throw new InsertException("BaseAimDBTemplate-insert-Exception");
        } finally {
          //  DBUtils.closeRandomAccessFile();
            System.out.println("");
        }
    }

    private  void writeByeType(RandomAccessFile raf, BaseTypeEnum baseTypeEnum, Object obj){

        try {
            switch (baseTypeEnum){
                case BYTE:
                    raf.writeByte(Integer.parseInt(String.valueOf(obj)));
                    break;
                case BOOLEAN:
                    raf.writeBoolean(Boolean.valueOf(obj.toString()));
                    break;
                case CHAR:
                    break;
                case DOUBLE:
                    break;
                case FLOAT:
                    break;
                case INT:
                    raf.writeInt(Integer.parseInt(String.valueOf(obj)));
                    break;
                case LONG:
                    break;
                case SHORT:
                    break;
                default:
                    break;
            }
        }catch (Exception e){

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
     * @param seek
     */
    public void selectBySeek(Object object, int seek) throws SeekException,AimFieldException {
        RandomAccessFile raf = null;
        try {
            raf = DBUtils.getRandomAccessFile();
            raf.seek(seek);
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f =  fields[i];
                f.setAccessible(true);
                if (f.getType().getName().equalsIgnoreCase("java.lang.String")) {
                    AimField aimField = f.getAnnotation(AimField.class);
                    if(aimField != null) {
                        int length = aimField.length();
                        byte[] buffer = new byte[length];
                        raf.read(buffer);
                        fields[i].set(object, new String(buffer).trim());
                       // seek += length;
                    }else {
                        throw  new AimFieldException("CommnUtils-interceptStr-AimFieldException");
                    }
                }else {
                    BaseTypeEnum b = CommnUtils.getBasicTypeEnum(f.getType().getName());
                    switch (b){
                        case INT:
                            fields[i].set(object, raf.readInt());
                            //seek = seek + b.getBytes()/8;
                            break;
                    }
                }
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
