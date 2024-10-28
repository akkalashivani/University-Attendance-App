package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class ViewAttendanceActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        databaseHelper = new DatabaseHelper(this);

        // Set up the ListView and populate with attendance records
        ListView listView = findViewById(R.id.listViewAttendance);
        SimpleCursorAdapter adapter = getSimpleCursorAdapter();

        // Set the adapter for the list view
        listView.setAdapter(adapter);
        Log.d("ViewAttendanceActivity", "Adapter set: " + true);

        // Clear attendance records immediately
        clearAttendance();
    }

    private @NonNull SimpleCursorAdapter getSimpleCursorAdapter() {
        // Retrieve the cursor from the database
        Cursor cursor = databaseHelper.getAllAttendance();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENT_NAME));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
                Log.d("ViewAttendanceActivity", "Student Name: " + name + ", Date: " + date);
            } while (cursor.moveToNext());
        }


        String[] fromColumns = {DatabaseHelper.COLUMN_STUDENT_NAME, DatabaseHelper.COLUMN_DATE};
        int[] toViews = {R.id.textStudentName, R.id.textAttendanceDate};

        // Return the SimpleCursorAdapter with the cursor data
        return new SimpleCursorAdapter(
                this,
                R.layout.list_item_attendance,
                cursor,
                fromColumns,
                toViews,
                0
        );
    }



    private void clearAttendance() {
        // Clear attendance records from the database
        databaseHelper.clearAttendance();
    }
}