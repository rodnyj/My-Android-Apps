package com.itec4550.currencyconverter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class FetchConversionRateTask extends AsyncTask<String, String, Double> {

    HttpURLConnection conn;


    Context context;
    double dValueToConvert;
    TextView tv;
    TextToSpeech textToSpeech;
    EditText value;
    static double rate = 0.0d;




    public FetchConversionRateTask(Context _context, double _dValueToConvert, TextView _textView, TextToSpeech _textToSpeech) {
        this.context = _context;
        this.tv = _textView;
        this.textToSpeech = _textToSpeech;
        this.dValueToConvert = _dValueToConvert;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    //    MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.ting);
    //    mediaPlayer.start();
    }

    @Override
    protected Double doInBackground(String... currencies) {
        String from = currencies[0];
        String to = currencies[1];

        try {
            URL url = new URL("http://api.fixer.io/latest?base=" + from + "&symbols=" + to);
            conn = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            Scanner scanner = new Scanner(inputStream);

            //REFACTORED BELOW


          //  Scanner scanner = null;
          //  scanner = new Scanner(new BufferedInputStream(httpURLConnection.getInputStream()));

            String sResult = "";

            while (scanner.hasNext()) sResult += scanner.nextLine();
/*
            JSONObject jsonObject = new JSONObject(sResult);
            JSONObject jsonObject2 = jsonObject.getJSONObject("rates");
            dRate = jsonObject2.getDouble(sTo);
*/
            //REFACTORED BELOW


            rate = new JSONObject(sResult).getJSONObject("rates").getDouble(to);

            scanner.close();

        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }
       // Log.i("","rate"+rate);
        return rate;
    }

    public void calculaterate (EditText amount){
        amount = (EditText) amount.findViewById(R.id.et_value);
        String myEditText = amount.getText().toString();
        Double value = Double.parseDouble(myEditText);

        double a = value / rate;
        Log.i("Amount:"," "+a);

        DecimalFormat format = new DecimalFormat("###.##");
        String msg = "Amount: " + format.format(a);

        tv.setText(msg);
    }


    @Override
    protected void onPostExecute(Double rate) {
        super.onPostExecute(rate);



        //MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.ting);
        //mediaPlayer.start();
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(sValueConverted, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(sValueConverted, TextToSpeech.QUEUE_FLUSH, null);
        }
*/
    }
}
