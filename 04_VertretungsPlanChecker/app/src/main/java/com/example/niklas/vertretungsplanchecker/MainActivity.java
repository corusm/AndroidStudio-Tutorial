package com.example.niklas.vertretungsplanchecker;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // View Elemente erstellen
    TextView planText;
    TextView planText2;

    // AsyncDownloadTask
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
                String planMorgen = jsonObject.getString("PlanMorgen");

                Log.i("heute", today);
                Log.i("morgen", tommorow);


                String out = "";
                JSONArray jsonArray = new JSONArray(planHeute);
                for (int i = 0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Log.i("Lehrer", jsonObject1.getString("Lehrer"));
                    Log.i("Stunde", jsonObject1.getString("Stunde"));

                    out = out + "\n" + jsonObject1.getString("Lehrer") + " - " + jsonObject1.getString("Stunde") + " - " + jsonObject1.getString("Fach");
                }


                // Text1
                planText = findViewById(R.id.planText);
                planText.setText(out);


                String out2 = "";
                JSONArray jsonArray2 = new JSONArray(planMorgen);
                for (int i = 0; i<jsonArray2.length(); i++) {
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(i);
                    Log.i("Lehrer", jsonObject2.getString("Lehrer"));
                    Log.i("Stunde", jsonObject2.getString("Stunde"));

                    out2 = out2 + "\n" + jsonObject2.getString("Lehrer") + " - " + jsonObject2.getString("Stunde") + " - " + jsonObject2.getString("Fach");
                }


                // Text2
                planText2 = findViewById(R.id.planText2);
                planText2.setText(out2);



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
