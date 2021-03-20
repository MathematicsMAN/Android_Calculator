package com.example.android_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private Calculator calculator = new Calculator();
    private final int lengthOfTextView = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button_num1);
        Button button2 = findViewById(R.id.button_num2);
        Button button3 = findViewById(R.id.button_num3);
        Button button4 = findViewById(R.id.button_num4);
        Button button5 = findViewById(R.id.button_num5);
        Button button6 = findViewById(R.id.button_num6);
        Button button7 = findViewById(R.id.button_num7);
        Button button8 = findViewById(R.id.button_num8);
        Button button9 = findViewById(R.id.button_num9);
        Button button0 = findViewById(R.id.button_num0);
        Button button_dot = findViewById(R.id.button_dot);
        Button button_equal = findViewById(R.id.button_equal);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_multiple = findViewById(R.id.button_multiply);
        Button button_division = findViewById(R.id.button_division);
        Button button_canceled = findViewById(R.id.button_cancel);
        textView = findViewById(R.id.textView_display);

        button0.setOnClickListener(v -> {
            pressNumberButton(0);
        });

        button1.setOnClickListener(v -> {
            pressNumberButton(1);
        });

        button2.setOnClickListener(v -> {
            pressNumberButton(2);
        });

        button3.setOnClickListener(v -> {
            pressNumberButton(3);
        });

        button4.setOnClickListener(v -> {
            pressNumberButton(4);
        });

        button5.setOnClickListener(v -> {
            pressNumberButton(5);
        });

        button6.setOnClickListener(v -> {
            pressNumberButton(6);
        });

        button7.setOnClickListener(v -> {
            pressNumberButton(7);
        });

        button8.setOnClickListener(v -> {
            pressNumberButton(8);
        });

        button9.setOnClickListener(v -> {
            pressNumberButton(9);
        });

        button_dot.setOnClickListener(v -> {
            calculator.choiceDot();
            refreshTextView();
        });

        button_plus.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.Division);
        });

        button_minus.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.Division);
        });

        button_multiple.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.Division);
        });

        button_division.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.Division);
        });

        button_equal.setOnClickListener(v -> {
            try {
                calculator.choiceEqual();
                refreshTextView();
            }
            catch (ArithmeticException e) {
                Log.e("Calculator",e.getMessage());
                calculator.choiceCancel();
                textView.setText("Err:Div 0!");
            }
        });

        button_canceled.setOnClickListener(v -> {
            calculator.choiceCancel();
            refreshTextView();
        });
    }

    private void pressNumberButton(int n) {
        calculator.choiceNumber(n);
        refreshTextView();
    }

    private void refreshTextView() {
        textView.setText(calculator.getOperator1InTextView(lengthOfTextView));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getSerializable("CALCULATOR");
        refreshTextView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("CALCULATOR",calculator);
    }

}