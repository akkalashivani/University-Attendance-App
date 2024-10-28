package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TakeAttendanceActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        databaseHelper = new DatabaseHelper(this);

        @SuppressLint("CutPasteId") LinearLayout layoutStudents = this.findViewById(R.id.layoutStudents);
        Button btnSaveAttendance = findViewById(R.id.btnSaveAttendance);

        String[] studentNames = { "Ram", "Shivani", "Bhavani", "Shiva", "Siri", "Krishna", "Radha", "Pooja", "Sita" }; // Ensure these names are correct
        for (String studentName : studentNames) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(studentName);
            layoutStudents.addView(checkBox);
        }

        btnSaveAttendance.setOnClickListener(v -> {
            List<String> selectedStudents = new ArrayList<>();

            LinearLayout layout = findViewById(R.id.layoutStudents);
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                if (child instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) child;
                    if (checkBox.isChecked()) {
                        selectedStudents.add(checkBox.getText().toString());
                    }
                }
            }

            // Iterate through selected students and insert attendance using insertAttendance method
            for (String student : selectedStudents) {
                String date = getCurrentDate(); // Get current date in desired format
                databaseHelper.insertAttendance(student, date); // Call your method here
            }

            Toast.makeText(TakeAttendanceActivity.this, "Attendance saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }
}
