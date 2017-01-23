package com.aimdb.env;

import com.aimdb.core.AimDatabase;
import com.aimdb.core.config.DBConfig;

/**
 * Created by bbking on 17-1-23.
 */
public class AimEnviroment implements  IAimEnviroment {

    private static  AimEnviroment aimEnviroment;
    public static AimEnviroment getInstance(){
        if(aimEnviroment == null)  aimEnviroment = new AimEnviroment();
        return aimEnviroment;
    }

    public void createEnviroment(String fileName) {

    }

    public void closeConnection(AimEnviroment aimEnviroment) {

    }

    public void closeDatabase(AimDatabase aimDatabase) {

    }

    public AimDatabase openConnection(AimEnviroment aimEnviroment, String database) {
        DBConfig dbConfig = new DBConfig();
        AimDatabase aimDatabase = new AimDatabase();


        return null;
    }
}
