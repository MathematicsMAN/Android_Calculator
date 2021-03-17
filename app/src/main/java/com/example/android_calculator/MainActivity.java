package com.example.android_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private final String messageDontKnow = "Не знаю(";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button24);
        Button button2 = findViewById(R.id.button25);
        Button button3 = findViewById(R.id.button26);
        Button button4 = findViewById(R.id.button27);
        Button button5 = findViewById(R.id.button28);
        Button button6 = findViewById(R.id.button29);
        Button button7 = findViewById(R.id.button30);
        Button button8 = findViewById(R.id.button31);
        Button button9 = findViewById(R.id.button32);
        Button button0 = findViewById(R.id.button33);
        Button button_dot = findViewById(R.id.button34);
        Button button_equal = findViewById(R.id.button35);
        Button button_plus = findViewById(R.id.button36);
        Button button_minus = findViewById(R.id.button37);
        Button button_multiple = findViewById(R.id.button38);
        Button button_division = findViewById(R.id.button39);
        Button button_canceled = findViewById(R.id.button40);
        textView = findViewById(R.id.textView);

        button0.setOnClickListener(v -> {
            appendSymbolInText("0");
        });

        button1.setOnClickListener(v -> {
            appendSymbolInText("1");
        });

        button2.setOnClickListener(v -> {
            appendSymbolInText("2");
        });

        button3.setOnClickListener(v -> {
            appendSymbolInText("3");
        });

        button4.setOnClickListener(v -> {
            appendSymbolInText("4");
        });

        button5.setOnClickListener(v -> {
            appendSymbolInText("5");
        });

        button6.setOnClickListener(v -> {
            appendSymbolInText("6");
        });

        button7.setOnClickListener(v -> {
            appendSymbolInText("7");
        });

        button8.setOnClickListener(v -> {
            appendSymbolInText("8");
        });

        button9.setOnClickListener(v -> {
            appendSymbolInText("9");
        });

        button_division.setOnClickListener(v -> {
            appendSymbolInText("/");
        });

        button_dot.setOnClickListener(v -> {
            appendSymbolInText(".");
        });

        button_multiple.setOnClickListener(v -> {
            appendSymbolInText("*");
        });

        button_minus.setOnClickListener(v -> {
            appendSymbolInText("-");
        });

        button_plus.setOnClickListener(v -> {
            appendSymbolInText("+");
        });

        button_equal.setOnClickListener(v -> {
            inputStringInText(messageDontKnow);
        });

        button_canceled.setOnClickListener(v -> {
            inputStringInText("0");
        });
    }

    private void appendSymbolInText(String s) {
        String whatInTextView = textView.getText().toString();
        if (messageDontKnow.equals(whatInTextView) || "0".equals(whatInTextView)){
            textView.setText(s);
        }
        else {
            textView.append(s);
        }
    }

    private void inputStringInText(String s) {
        textView.setText(s);
    }

}