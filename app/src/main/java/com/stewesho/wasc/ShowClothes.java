package com.stewesho.wasc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class ShowClothes extends AppCompatActivity {

    JSONObject darkskyData;
    JSONObject weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clothes);

        //Powered By Dark Sky
        Spanned powered_by;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            powered_by = Html.fromHtml(getString(R.string.powered_by), Html.FROM_HTML_MODE_LEGACY);
        else
            powered_by = Html.fromHtml(getString(R.string.powered_by));
        TextView textPoweredBy = (TextView) findViewById(R.id.textPoweredBy);
        textPoweredBy.setText(powered_by);
        textPoweredBy.setMovementMethod(LinkMovementMethod.getInstance());

        Intent intent = getIntent();

        double lat = intent.getDoubleExtra("latitude", 0.0);
        double lng = intent.getDoubleExtra("longitude", 0.0);

        TextView coordinateText = findViewById(R.id.textCoords);

        coordinateText.setText(String.format(Locale.CANADA, "(%f, %f)", lat, lng));

        String json_string;
        Toast toast_test;
        try{
            json_string = new GetWeatherTask().execute(lat, lng).get();
            darkskyData = new JSONObject(json_string);

            weather = darkskyData.getJSONObject("daily").getJSONArray("data").getJSONObject(0);


            toast_test = Toast.makeText(getApplicationContext(), weather.has("uvIndex") ? "yes" : "no", Toast.LENGTH_LONG);
            toast_test.show();
        } catch (JSONException je){
            Log.e("Invalid JSON file.", je.getMessage(), je);
            Toast toast_error = Toast.makeText(getApplicationContext(), "oOoopSIE dooPSie", Toast.LENGTH_LONG);
            toast_error.show();
        } catch (Exception e){
            // . . . lol did you think i would actually handle this??!?!
        }

    }

    class GetWeatherTask extends AsyncTask<Double, Void, String> {
        final private String KEY = "846ce2443ae544622e0442e0f44d1f95";
        private StringBuilder api_address = new StringBuilder();

        protected void onPreExecute(){
            api_address.append("https://api.darksky.net/forecast/").append(KEY).append("/");
        }

        protected String doInBackground(Double... coordinates){
            Double latitude = coordinates[0];
            Double longitude = coordinates[1];
            api_address.append(latitude.toString()).append(",").append(longitude.toString()).append("?exclude=currently,minutely,hourly,flags&units=ca");

            try {
                URL api_url = new URL(api_address.toString());
                HttpURLConnection api_connection = (HttpURLConnection) api_url.openConnection();
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(api_connection.getInputStream()));
                    StringBuilder json_file = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        json_file.append(line).append("\n");
                    }
                    reader.close();
                    return json_file.toString();
                } finally {
                    api_connection.disconnect();
                }

            } catch (Exception e){
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
    }
}


