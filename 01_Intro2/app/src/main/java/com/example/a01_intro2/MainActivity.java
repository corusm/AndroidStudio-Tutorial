package com.example.a01_intro2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String today = jsonObject.getString("dayOfHeute");
                String tommorow = jsonObject.getString("dayOfMorgen");
                String planHeute = jsonObject.getString("PlanHeute");

                Log.i("heute", today);
                Log.i("morgen", tommorow);


                JSONArray jsonArray = new JSONArray(planHeute);
                for (int i = 0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Log.i("Lehrer", jsonObject1.getString("Lehrer"));
                    Log.i("Stunde", jsonObject1.getString("Stunde"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.i("JSON", s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DownloadTask task = new DownloadTask();

        try {
            task.execute("https://root.corusm.de/api/timetable?klasse=13");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
