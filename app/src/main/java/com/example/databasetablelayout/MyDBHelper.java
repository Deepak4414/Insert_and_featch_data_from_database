package com.example.databasetablelayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "deepak";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_REGNO = "regno";
    public static final String KEY_COURSE = "course";
    public static final String KEY_BRANCH = "branch";
    public static final String KEY_SEM = "sem";
    public static final String KEY_SECTION = "section";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_REGNO + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_COURSE + " TEXT,"
                + KEY_BRANCH + " TEXT,"
                + KEY_SEM + " TEXT,"
                + KEY_SECTION + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public long addContact(String regno, String name, String course, String branch, String sem, String section) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REGNO, regno);
        values.put(KEY_NAME, name);
        values.put(KEY_COURSE, course);
        values.put(KEY_BRANCH, branch);
        values.put(KEY_SEM, sem);
        values.put(KEY_SECTION, section);

        long result = db.insert(TABLE_CONTACTS, null, values);
        db.close();
        return result;
    }

    public Cursor getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_CONTACTS, null, null, null, null, null, null);
    }

    // Other query methods can be added here based on your requirements
}
