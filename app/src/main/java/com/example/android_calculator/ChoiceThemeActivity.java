package com.example.android_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class ChoiceThemeActivity extends AppCompatActivity {

    public static final int AppThemeLightCodeStyle = 0;
    public static final int AppThemeDarkCodeStyle = 1;
    public static final String CODE_STYLE = "CODE_STYLE";

    Intent intentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_theme);

        intentResult = new Intent();
        setResult(RESULT_CANCELED,intentResult);

        findViewById(R.id.button_return_back).setOnClickListener(v ->
                finish());

        initThemeChooser();
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMaterialDark),
                AppThemeDarkCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLight),
                AppThemeLightCodeStyle);
    }

    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(v -> {
            intentResult.putExtra(CODE_STYLE,codeStyle);
            setResult(RESULT_OK,intentResult);
        });
    }
}