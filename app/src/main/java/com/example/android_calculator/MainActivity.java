package com.example.android_calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String KEY_CALCULATOR = "CALCULATOR";
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    private static final String NameSharedPreference = "LOGIN";
    private static final String appTheme = "APP_THEME";
    private final static String FIRST_OPERATOR = "OPERATOR1";

    TextView textView;
    private Calculator calculator = new Calculator();
    ActionBar actionBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.AppThemeLight));
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView_display);

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
        Button button_multiply = findViewById(R.id.button_multiply);
        Button button_division = findViewById(R.id.button_division);
        Button button_canceled = findViewById(R.id.button_cancel);

        button0.setOnClickListener(v -> pressNumberButton(0));
        button1.setOnClickListener(v -> pressNumberButton(1));
        button2.setOnClickListener(v -> pressNumberButton(2));
        button3.setOnClickListener(v -> pressNumberButton(3));
        button4.setOnClickListener(v -> pressNumberButton(4));
        button5.setOnClickListener(v -> pressNumberButton(5));
        button6.setOnClickListener(v -> pressNumberButton(6));
        button7.setOnClickListener(v -> pressNumberButton(7));
        button8.setOnClickListener(v -> pressNumberButton(8));
        button9.setOnClickListener(v -> pressNumberButton(9));

        button_dot.setOnClickListener(v -> {
            calculator.choiceDot();
            refreshTextView();
        });

        button_plus.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.PLUS);
            refreshTextView();
        });

        button_minus.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.MINUS);
            refreshTextView();
        });

        button_multiply.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.MULTIPLY);
            refreshTextView();
        });

        button_division.setOnClickListener(v -> {
            calculator.choiceAction(Calculator.Action.DIVISION);
            refreshTextView();
        });

        button_equal.setOnClickListener(v -> {
            try {
                calculator.choiceEqual();
                refreshTextView();
            } catch (ArithmeticException e) {
                Log.e("Calculator", e.getMessage());
                calculator.choiceCancel();
                textView.setText(R.string.error_message);
            } catch (NullPointerException | NumberFormatException e) {
                Log.e("Calculator", e.getMessage());
                calculator.choiceCancel();
                textView.setText(R.string.error_type_mismatch);
            }
        });

        button_canceled.setOnClickListener(v -> {
            calculator.choiceCancel();
            refreshTextView();
        });

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setTitle(R.string.title_menu);
            actionBar.show();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        String operator1 = bundle.getString(FIRST_OPERATOR);
        calculator.setOperator1(operator1);
        refreshTextView();
    }

    private void pressNumberButton(int n) {
        calculator.choiceNumber(n);
        refreshTextView();
    }

    private void refreshTextView() {
        textView.setText(calculator.getOperator1());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getSerializable(KEY_CALCULATOR);
        refreshTextView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_CALCULATOR, calculator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menu_choice_theme:
                Intent intent = new Intent(this, ChoiceThemeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTING_ACTIVITY);
                break;
            case R.id.menu_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        int codeStyle = 0;
        if (data != null) {
            codeStyle = data.getIntExtra(ChoiceThemeActivity.CODE_STYLE, 0);
        }

        if (resultCode == RESULT_OK) {
            setAppTheme(codeStyle);
            recreate();
        }
    }

    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(appTheme, codeStyle);
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case ChoiceThemeActivity.AppThemeLightCodeStyle:
                return R.style.AppThemeLight;
            case ChoiceThemeActivity.AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
        }
        return R.style.AppThemeLight;
    }
}