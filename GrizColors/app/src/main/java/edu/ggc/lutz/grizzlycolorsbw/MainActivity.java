package edu.ggc.lutz.grizzlycolorsbw;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "GrizzlyColorsBW";

    private SeekBar sbGray;
    private SeekBar sbBlue;
    private SeekBar sbRed;
    private SeekBar sbAlpha;
    private TextView swatch;
    private CoordinatorLayout cLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        sbGray = (SeekBar) findViewById(R.id.sbGray);
        sbRed = (SeekBar) findViewById(R.id.sbRed);
        sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        sbAlpha = (SeekBar) findViewById(R.id.sbAlpha);

        sbAlpha.setProgress(255);

        swatch = (TextView) findViewById(R.id.tvswatch);


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.topRelativeLayout);
        layout.getBackground().setAlpha(120);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int green = sbGray.getProgress();
                int red = sbRed.getProgress();
                int blue = sbBlue.getProgress();
                int alpha = sbAlpha.getProgress();

                int bigColor = Color.argb(alpha, green, red, blue);

                swatch.setBackgroundColor(bigColor);
                swatch.setTextColor(Color.argb(255,255-green, 255-red, 255-blue));

                String hexAlpha = Integer.toHexString(0x100 | alpha).substring(1).toUpperCase();
                String hexGreen = Integer.toHexString(0x100 | green).substring(1).toUpperCase();
                String hexRed = Integer.toHexString(0x100 | red).substring(1).toUpperCase();
                String hexBlue = Integer.toHexString(0x100 | blue).substring(1).toUpperCase();

                swatch.setText("#" + hexAlpha   + hexRed + hexGreen + hexBlue);


            }

        });


        Button btnAbout = (Button) findViewById(R.id.btnAbout);

        cLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snackbar = Snackbar.make(cLayout, " Created by: Rodny Joseph Jun 2016\n Swipe right to dismiss ",Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        });


        //TODO add a button for 'About'
        //TODO add a click listener for about button, generate a toast, snackbar or second activity

    }


}
