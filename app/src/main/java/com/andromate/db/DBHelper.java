package com.andromate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

import static androidx.room.RoomMasterTable.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "androdb";
    private static final int DATABASE_VERSION = 5;

    // Table Names
    private static final String TABLE_MACRO = "macros";
    private static final String TABLE_TRIGGER = "triggers";
    private static final String TABLE_ACTION = "action";
    private static final String TABLE_CONSTRAINTS = "constraints";
    /* private static final String TABLE_USERS = "users";*/

    // macro Table Columns
    private static final String KEY_MACRO_ID = "id";
    private static final String MACRO_NAME = "macroname";
    private static final String MACRO_DES = "macrodes";
    private static final String MACRO_CATEGORY = "category";
    private static final String MACRO_STATE = "macrostate";
    private static final String ACTIVE_TIME = "activetime";


    //Trigger Column item
    private static final String TRIGGERID = "Trig_id";
    private static final String TRIGGER_NAME = "triggername";
    private static final String TRIGGER_DES = "triggerdes";



    private static final String ACT_ID = "act_id";
    private static final String ACTION_NAME = "actionname";
    private static final String ACTION_DES = "actiondes";



    private static final String CONS_ID = "cons_id";
    private static final String CONS_NAME = "cons_name";
    private static final String CONS_DES = "cons_des";
    // User Table Columns
    /*private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PROFILE_PICTURE_URL = "profilePictureUrl";*/

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_MACRO_TABLE = "CREATE TABLE " + TABLE_MACRO +
                "(" +
                KEY_MACRO_ID + "INTEGER PRIMARY KEY," + // Define a primary key
                MACRO_NAME + "TEXT," + MACRO_DES + "TEXT," + // Define a foreign key
                MACRO_STATE + "TEXT," + ACTIVE_TIME + "TEXT" +
                ")";*/


        String query = "CREATE TABLE " + TABLE_MACRO + " (" +
                KEY_MACRO_ID + " INTEGER, " +
                MACRO_NAME + " TEXT, " +
                MACRO_DES + " TEXT, " +
                MACRO_STATE + " TEXT, " +
                MACRO_CATEGORY + " TEXT, " +
                ACTIVE_TIME + " TEXT); ";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_TRIGGER + " (" +
                TRIGGERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MACRO_ID + " INTEGER, " +
                TRIGGER_NAME + " TEXT, " +
                TRIGGER_DES + " TEXT); ";

        db.execSQL(query2);



        String query_action = "CREATE TABLE " + TABLE_ACTION + " (" +
                ACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MACRO_ID + " INTEGER, " +
                ACTION_NAME + " TEXT, " +
                ACTION_DES + " TEXT); ";

        db.execSQL(query_action);


        String query_constraints = "CREATE TABLE " + TABLE_CONSTRAINTS + " (" +
                CONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MACRO_ID + " INTEGER, " +
                CONS_NAME + " TEXT, " +
                CONS_DES + " TEXT); ";

        db.execSQL(query_constraints);



       /* String searchQ="SELECT " +TABLE_TRIGGER+"*, "+TABLE_ACTION+"*, "+TABLE_CONSTRAINTS+"FROM "+TABLE_MACRO+"JOIN as"+TABLE_TRIGGER*/


       /* String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_PROFILE_PICTURE_URL + " TEXT" +
                ")";*/

        /*db.execSQL(CREATE_MACRO_TABLE);*/
        /*db.execSQL(CREATE_USERS_TABLE);*/


    }

    public Cursor getmacro(){
        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_MACRO,null);
        return cursor;

    }


    public Cursor getTriggers(String id){
        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_TRIGGER+" WHERE id ="+"'"+id+"'",null);
        Log.d("dsdsddddd","fff"+cursor);
        return cursor;

    }


    public Cursor getAction(String id){
        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_ACTION+" WHERE id ="+"'"+id+"'",null);
        return cursor;

    }

    public Cursor getConstraints(String id){
        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_CONSTRAINTS+" WHERE id ="+"'"+id+"'",null);
        return cursor;

    }



    public Cursor checktrigger(String triggername){
        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select* from "+TABLE_TRIGGER+" WHERE triggername ="+"'"+triggername+"'",null);
        return cursor;

    }






    public void updatemacro(String time,String id){
        SQLiteDatabase db =  this.getWritableDatabase();

        String sql="update "+TABLE_MACRO+" SET activetime ="+"'"+time+"'"+" WHERE id ="+"'"+id+"'";
       /* Cursor cursor=db.update(,null);*/
        db.execSQL(sql);


    }


    public void insertMacro(int id, String macroname, String macro_des, String state, String active_time, String category) {

        SQLiteDatabase db = getWritableDatabase();

        Log.d("kjflkdjflkdflk", "okkk" + TABLE_MACRO);
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("macroname", macroname);
        values.put("macrodes", macro_des);
        values.put("macrostate", state);
        values.put("category", category);
        values.put("activetime", active_time);
        database.insert(TABLE_MACRO, null, values);
        database.close();
    }


    public void insertTrigger(int id, String triggername, String triggerdes) {

        SQLiteDatabase db = getWritableDatabase();

        Log.d("kjflkdjflkdflk", "okkk" + TABLE_MACRO);
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("triggername", triggername);
        values.put("triggerdes", triggerdes);
        database.insert(TABLE_TRIGGER, null, values);
        database.close();
    }


    public void insertAction(int id, String actionname, String action_des) {

        SQLiteDatabase db = getWritableDatabase();

        Log.d("kjflkdjflkdflk", "okkk" + TABLE_MACRO);
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put(ACTION_NAME, actionname);
        values.put(ACTION_DES, action_des);
        database.insert(TABLE_ACTION, null, values);
        database.close();
    }


    public void insertConstraints(int id, String consname, String cons_des) {

        SQLiteDatabase db = getWritableDatabase();
        Log.d("kjflkdjflkdflk", "okkk" + TABLE_CONSTRAINTS);
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put(CONS_NAME, consname);
        values.put(CONS_DES, cons_des);
        database.insert(TABLE_CONSTRAINTS, null, values);
        database.close();
    }



    public void printTableData() {
        Log.d("dfdfdfdf", "enter");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_MACRO, null);
        Log.d("dfdfdfdf", "counter" + cur.getCount());
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            do {
                String row_values = "";
                for (int i = 0; i < cur.getColumnCount(); i++) {
                    row_values = row_values + " || " + cur.getString(i);
                    Log.d("dfdfdfdf", row_values);
                }
            } while (cur.moveToNext());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MACRO);
            /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);*/
            onCreate(db);
        }
    }
}
