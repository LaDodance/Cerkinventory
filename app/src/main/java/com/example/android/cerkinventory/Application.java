package com.example.android.cerkinventory;

import android.database.sqlite.SQLiteDatabase;


import com.example.android.cerkinventory.db.DBHelper;

import java.io.File;


public class Application extends android.app.Application{

    private static Application instance;
    // DB
    private DBHelper generalDbHelper;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getGeneralDbHelper().getReadableDatabase();
    }


    public DBHelper getGeneralDbHelper() {
        if (generalDbHelper == null) {
            File DB_PATH = getApplicationContext().getDatabasePath(DBHelper.DATABASE_NAME);
            DB_PATH.getParentFile().mkdirs();
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH, null);
            db.close();
            generalDbHelper = new DBHelper(this);
        }
        return generalDbHelper;
    }

}
