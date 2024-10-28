package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "attendance.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ATTENDANCE = "attendance";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STUDENT_NAME = "student_name";
    public static final String COLUMN_DATE = "attendance_date";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_ATTENDANCE + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_STUDENT_NAME + " TEXT, " +
            COLUMN_DATE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        onCreate(db);
    }

    public void clearAttendance() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ATTENDANCE, null, null);
        db.close();
    }
    public Cursor getAllAttendance() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ATTENDANCE, null, null, null, null, null, null); // Retrieve all records from the attendance table
    }
    public void insertAttendance(String studentName, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, studentName);
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_ATTENDANCE, null, values);
        db.close();
    }




}