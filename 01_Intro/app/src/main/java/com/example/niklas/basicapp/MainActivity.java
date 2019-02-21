package com.example.niklas.basicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    activity_main.xml

    - create Button
    - create TextView
    - add constraints
    - add changeText() to Button onClick

     */


    // Static vars
    TextView editText;

    public void changeText(View view) { // Import View from activity_main.xml
        editText = findViewById(R.id.editText); // import editText
        Log.i("Button Pressed", Integer.toString(view.getId())); // Logging button with ID
        Toast.makeText(MainActivity.this, editText.getText(), Toast.LENGTH_LONG).show(); // Make a Toast with content of Button
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
