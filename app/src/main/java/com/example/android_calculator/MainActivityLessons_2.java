package com.example.android_calculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLessons_2 extends AppCompatActivity {
    private static final String TAG = "MainActivityLessons2";
    private Counters counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lessons);
        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
        }
        else{
            instanceState = "Повторный запуск!";
        }
        MakeToast( instanceState+" - onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MakeToast("onRestart()");

    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        MakeToast( "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        Toast.makeText(getApplicationContext(), "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        MakeToast( "onRestoreInstanceState()");
        counter = (Counters) savedInstanceState.getSerializable("COUNTERS");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        MakeToast( "onResume()");
    }
//============ Здесь "выполняется" программа =================//
    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        MakeToast( "onPause()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        MakeToast( "onSaveInstanceState()");
        outState.putSerializable("COUNTERS",counter);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        MakeToast( "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MakeToast( "onDestroy()");
//        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    private void MakeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, message);
    }

}