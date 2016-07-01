package com.itec4550.currencyconverter;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout main;
    EditText value;
    Spinner spFrom, spTo;
    Button btConvert, btAbout;
    TextView result;

    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main = (CoordinatorLayout) findViewById(R.id.cl_main);
        value = (EditText) findViewById(R.id.et_value);
        spFrom = (Spinner) findViewById(R.id.sp_from);
        spTo = (Spinner) findViewById(R.id.sp_to);
        btConvert = (Button) findViewById(R.id.bt_convert);
        result = (TextView) findViewById(R.id.tv_result);
        btAbout = (Button) findViewById(R.id.bt_about);

 //       RelativeLayout rl = (RelativeLayout)findViewById(R.id.backgroundColor);
 //       rl.setBackgroundColor(Color.GRAY);

        value.setText("0");
        result.setText("");

        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double dValue = 0d;
                try {
                    dValue = Double.parseDouble(value.getText().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();

                    value.setText(dValue.toString());
                }

                if (dValue == 0) {
                    Snackbar snackbar = Snackbar.make(main, "'Value' to convert in not valid", Snackbar.LENGTH_LONG);
                    snackbar.show();

                    return;
                }

                String sFrom = spFrom.getSelectedItem().toString();
                String sTo = spTo.getSelectedItem().toString();

                if (sFrom.equals(sTo)) {
                    Snackbar snackbar = Snackbar.make(main, "'From' and 'To' values should be different to perform the conversion", Snackbar.LENGTH_LONG);
                    snackbar.show();

                    return;
                }

                new FetchConversionRateTask(getApplicationContext(), dValue, result, textToSpeech).execute(sFrom, sTo);
                new FetchConversionRateTask(getApplicationContext(), dValue, result, textToSpeech).calculaterate(value);
            }
        });

        btAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(main, "Rodny Joseph\nCreated on 06/8/2016", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
/*
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
*/
    }
}
