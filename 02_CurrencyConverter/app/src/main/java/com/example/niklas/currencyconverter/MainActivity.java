package com.example.niklas.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void currencyConverter(View view) {
        EditText editText1 = findViewById(R.id.editText);
        String myText = editText1.getText().toString();
        double numb = Double.parseDouble(myText);
        numb = numb * (1/1.2);
        String outText = String.format("%.2f", numb);
        TextView textView1 = findViewById(R.id.textView2);
        textView1.setText(outText + "$");
        Toast.makeText(this, outText, Toast.LENGTH_LONG).show();
    }
}
