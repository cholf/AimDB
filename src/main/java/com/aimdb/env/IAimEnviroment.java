package com.aimdb.env;

import com.aimdb.core.AimDatabase;

/**
 * Created by bbking on 17-1-23.
 */
public interface IAimEnviroment {

    public void createEnviroment(String fileName);

    public void closeConnection(AimEnviroment aimEnviroment);

    public void closeDatabase(AimDatabase aimDatabase);

    public AimDatabase openConnection(AimEnviroment aimEnviroment, String database);
}
