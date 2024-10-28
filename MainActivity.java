package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTakeAttendance).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TakeAttendanceActivity.class)));

        findViewById(R.id.btnViewAttendance).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewAttendanceActivity.class)));
    }
}