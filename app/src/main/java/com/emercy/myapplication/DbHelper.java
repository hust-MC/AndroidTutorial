package com.emercy.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "herodb";
    private static final String TABLE_Heres = "herodetails";
    private static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_POS = "position";
    static final String KEY_DESG = "designation";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Heres + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_POS + " TEXT,"
                + KEY_DESG + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

        initHero(db, "安琪拉","中路", "草丛");
        initHero(db, "马超","对抗路", "帅气");
        initHero(db, "伽罗","发育路", "禁掉");
        initHero(db, "鲁班","发育路", "手长");
        initHero(db, "兰陵王","打野", "绕后");
        initHero(db, "猴子","打野", "爆发");
    }

    private void initHero(SQLiteDatabase db, String name, String location, String designation) {
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_POS, location);
        cValues.put(KEY_DESG, designation);
        long newRowId = db.insert(TABLE_Heres, null, cValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Heres);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails(String name, String location, String designation) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_POS, location);
        cValues.put(KEY_DESG, designation);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Heres, null, cValues);
        db.close();
    }

    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        String query = "SELECT " + KEY_NAME + ", " + KEY_POS + ", " + KEY_DESG + " FROM " + TABLE_Heres;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put(KEY_NAME, cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put(KEY_POS, cursor.getString(cursor.getColumnIndex(KEY_POS)));
            user.put(KEY_DESG, cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            userList.add(user);
        }
        return userList;
    }

    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM " + TABLE_Heres;
        Cursor cursor = db.query(TABLE_Heres, new String[]{KEY_NAME, KEY_POS, KEY_DESG}, KEY_ID + "=?", new String[]{String.valueOf(userid)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation", cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            user.put("location", cursor.getString(cursor.getColumnIndex(KEY_POS)));
            userList.add(user);
        }
        return userList;
    }

    // Delete User Details
    public void DeleteUser(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Heres, KEY_ID + " = ?", new String[]{String.valueOf(userid)});
        db.close();
    }

    // Update User Details
    public int UpdateUserDetails(String location, String designation, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_POS, location);
        cVals.put(KEY_DESG, designation);
        int count = db.update(TABLE_Heres, cVals, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        return count;
    }
}