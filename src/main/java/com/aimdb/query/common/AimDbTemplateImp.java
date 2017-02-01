package com.aimdb.query.common;

import com.aimdb.anotation.AimField;
import com.aimdb.anotation.AimIndex;
import com.aimdb.enums.CriteriaTypeEnum;
import com.aimdb.query.AimQuery;
import com.aimdb.query.model.Criteria;
import com.aimdb.utils.CommnUtils;
import com.aimdb.utils.DBUtils;
import com.aimdb.container.BP.BPTree;
import com.aimdb.enums.BaseTypeEnum;
import com.aimdb.exception.AimFieldException;
import com.aimdb.exception.DataAccessException;
import com.aimdb.query.api.AimDbTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by bbking on 17-2-1.
 */
public class AimDbTemplateImp  implements AimDbTemplate{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    BPTree<String, Integer> bt =  DBUtils.getBPTree();

    public <T> T selectOne(Object obj, AimQuery aq) throws DataAccessException {
        RandomAccessFile raf = null;
        try {
            raf = DBUtils.getRandomAccessFile();
            long seek = 0;
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            seek = getSeek( declaredFields,aq);
            for (int i = 0; i < declaredFields.length; i++) {
                Field f =  declaredFields[i];
                f.setAccessible(true);
                raf.seek(seek);
                if (f.getType().getName().equalsIgnoreCase("java.lang.String")) {
                    AimField aimField = f.getAnnotation(AimField.class);
                    if(aimField != null) {
                        int length = aimField.length();
                        byte[] buffer = new byte[length];
                        raf.read(buffer);
                        declaredFields[i].set(obj, new String(buffer).trim());
                        seek += length;
                    }else {
                        throw  new AimFieldException("CommnUtils-interceptStr-AimFieldException");
                    }
                }else {
                    BaseTypeEnum b = CommnUtils.getBasicTypeEnum(f.getType().getName());
                    switch (b){
                        case INT:
                            declaredFields[i].set(obj, raf.readInt());
                            seek += b.getBytes()/8;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("BaseAimDBTemplate-selectBySeek,e:{}", e);
            throw new DataAccessException("BaseAimDBTemplate-selectBySeek-Exception");
        }finally {
            DBUtils.closeRandomAccessFile();
        }
        System.out.println(obj.toString());
        return null;
    }

    private int getSeek(Field[] declaredFields, AimQuery aq) {
        Criteria criteria = null;
        for (Field f:declaredFields){
            AimIndex aimIndex = f.getAnnotation(AimIndex.class);
            if(aimIndex != null) {
                criteria =(aq.getCriteriaMap()).get(f.getName());
            }
        }
        
        if(criteria != null){
            return bt.get(criteria.getValue().toString());
            
        }else {
            
        }
        /*CriteriaTypeEnum typeEnum;
        switch (typeEnum){
            case EQ:

                break;
            case GTE:
                break;
            case GT:
                break;
            case LT:
                break;
            case LTE:
                break;
            default:
                break;
        }*/
        return 0;
    }

    public <T> List<T> selectForLists(Class<T> c, AimQuery aq) throws DataAccessException {
        return null;
    }

    public boolean insert(Object o) throws DataAccessException {
        try {
            RandomAccessFile raf = DBUtils.getRandomAccessFile();
            Field[] fields = o.getClass().getDeclaredFields();
            int seek = 0;
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                seek = (int) raf.getFilePointer();
                AimIndex aimIndex = f.getAnnotation(AimIndex.class);
                if(aimIndex != null) {
                    bt.put( f.get(o).toString(), seek);
                }
                if (f.getType().getName().equalsIgnoreCase("java.lang.String")) {
                    Map<String, Object> map = CommnUtils.interceptStr((f.get(o)).toString(), f);
                    raf.write((map.get(CommnUtils.STRING).toString()).getBytes());
                } else {
                    writeByeType(raf, CommnUtils.getBasicTypeEnum(f.getType().getName()), f.get(o));
                    System.out.println("" + f.get(o));
                }
            }
        } catch (Exception e) {
            logger.error("BaseAimDBTemplate-insert,e:{}", e);
            throw new DataAccessException("BaseAimDBTemplate-insert-Exception");
        } finally {
            //  DBUtils.closeRandomAccessFile();
            System.out.println("insert-finally");
            DBUtils.writeObj2File("btree.obj",bt);
            System.out.println(bt.toString());
        }
        return false;
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
}
