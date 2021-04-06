package com.andromate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static androidx.room.RoomMasterTable.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "andromate.db";
    public static final String TABLENAME_TRIGGER = "triggertable";
    public static final String TABLENAME_ACTION = "actiontable";
    public static final String TABLENAME_CONSTRAINTS = "constraintstable";
    public static final String MACRO_TABLE = "mactotable";

    public static final String ID = "id";
    public static final String MACRO_NAME = "macro_name";
    public static final String MACRO_DES = "macro_des";

    public static final String TRIGGERNAME = "triggername";
    public static final String TRIGGER_DES = "trigger_des";
    public static final String ACTION_NAME = "actionname";
    public static final String ACTION_NAME_DES = "actiondes";
    public static final String CONSTRAINTSNAME = "constraints_name";
    public static final String CONSTRAINTS_DES = "constraints_des";
    public static final String ICON = "icon";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + MACRO_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,MACRONAME TEXT,MACRODES TEXT)");
        db.execSQL("create table " + TABLENAME_TRIGGER +" (ID INTEGER,TRIGGERNAME TEXT,TRIGGER_DES TEXT,ICON INTEGER)");
        db.execSQL("create table " + TABLENAME_ACTION +" (ID INTEGER,ACTION_NAME TEXT,ACTION_NAME_DES TEXT,ICON INTEGER)");
        db.execSQL("create table " + TABLENAME_CONSTRAINTS +" (ID INTEGER,CONSTRAINTSNAME TEXT,CONSTRAINTS_DES TEXT,ICON INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME_TRIGGER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME_ACTION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME_CONSTRAINTS);
        onCreate(db);
    }
    public boolean insertDataMacro(String macroname,String macrodes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MACRO_NAME,macroname);
        contentValues.put(MACRO_DES,macrodes);
        long result = db.insert(TABLENAME_TRIGGER,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean insertDataTriggers(int id,String triggername,String triggers_des,int icon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(TRIGGERNAME,triggername);
        contentValues.put(TRIGGER_DES,triggers_des);
        contentValues.put(ICON,icon);
        long result = db.insert(TABLENAME_TRIGGER,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean insertDataAction(int id,String actionname,String action_des,int icon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(ACTION_NAME,actionname);
        contentValues.put(ACTION_NAME_DES,action_des);
        contentValues.put(ICON,icon);
        long result = db.insert(TABLENAME_ACTION,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataAConstraints(int id,String actionname,String action_des,int icon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(CONSTRAINTSNAME,actionname);
        contentValues.put(CONSTRAINTS_DES,action_des);
        contentValues.put(ICON,icon);
        long result = db.insert(TABLENAME_ACTION,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllDataMacro() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+MACRO_TABLE,null);
        return res;
    }

    public Cursor getAllDatatTrigger() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLENAME_TRIGGER,null);
        return res;
    }
    public Cursor getAllDatatAction() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLENAME_ACTION,null);
        return res;
    }

    public Cursor getAllDatatConstraints() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLENAME_CONSTRAINTS,null);
        return res;
    }

    public Integer deleteDataMacro (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MACRO_TABLE, "ID = ?",new String[] {id});
    }

    public Integer deleteDataTrigger (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLENAME_TRIGGER, "ID = ?",new String[] {id});
    }

    public Integer deleteDataAction (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLENAME_ACTION, "ID = ?",new String[] {id});
    }
    public Integer deleteDataConstraintso (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLENAME_CONSTRAINTS, "ID = ?",new String[] {id});
    }

}
