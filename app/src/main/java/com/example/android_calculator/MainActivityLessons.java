package com.example.android_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivityLessons extends AppCompatActivity {
    private static final String TAG = "MainActivityLessons1";
    private Counters counters;

    //    private int counter1 = 0;       // первый счетчик
//    private int counter2 = 0;       // второй счетчик
    private TextView textCounter1;  // пользовательский элемент 1-го счетчика
    private TextView textCounter2;  // пользовательский элемент 2-го счетчика

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_lessons);
        setContentView(R.layout.activity_main_lesson2_2);

        counters = new Counters();
        initView(savedInstanceState);

    }

    private void initView(Bundle savedInstanceState) {
        textCounter1 = findViewById(R.id.textView_1);
        textCounter2 = findViewById(R.id.textView_2);

// Обработка нажатия кнопки через ClickListener
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(v -> {
//            counter2++;
//            textCounter2.setText(String.format(Locale.getDefault(), "%d", counter2));
            counters.incrementCounter2();
            textCounter2.setText(String.format(Locale.getDefault(),
                    "%d", counters.getCounter2()));
        });

// Обработка нажатия кнопки через создание обекта с методом ClickListener
// Отличается от предыдущего - только создание ещё одной лишней переменной.
 /*       View.OnClickListener onClickListener = v -> {
            textCounter2.setText(String.format(Locale.getDefault(), "%d", counter2));
        };
        button2.setOnClickListener(onClickListener);
*/
        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }
        MakeToast(instanceState + " - onCreate()");

// Попытка из данной активити запустить другую активити и посмотреть, какие этапы жизненного цикла произойдут
  /*      findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName(
                    "com.example.android_calculator",
                    "com.example.android_calculator.MainActivityLessons_2");
            intent.setComponent(componentName);
            startActivity(intent);
        });
*/
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
        MakeToast("onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        Toast.makeText(getApplicationContext(), "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        MakeToast("onRestoreInstanceState()");
        counters = (Counters) savedInstanceState.getSerializable("COUNTERS");
        textCounter1.setText(String.format(Locale.getDefault(), "%d", counters.getCounter1()));
        textCounter2.setText(String.format(Locale.getDefault(), "%d", counters.getCounter2()));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        MakeToast("onResume()");
    }

    //============ Здесь "выполняется" программа =================//
    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        MakeToast("onPause()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        MakeToast("onSaveInstanceState()");
        outState.putSerializable("COUNTERS", counters);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        MakeToast("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MakeToast("onDestroy()");
//        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    private void MakeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, message);
    }

    //Этот метод не очень хороший, т.к. использует рефлексию и название методоа считывается из файла
    //и это только используется для класса activity
    public void button1_onClick(View view) {
//        counter1++;
//        textCounter1.setText(String.format(Locale.getDefault(), "%d", counter1));
        counters.incrementCounter1();
        textCounter1.setText(String.format(Locale.getDefault(), "%d", counters.getCounter1()));
    }
}