package com.example.niklas.animations;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bart = findViewById(R.id.imageViewBart);
        bart.setX(-10000);
        bart.animate().translationXBy(10000).rotation(3600).setDuration(2000);
    }

    // private boolean bartisshowing = true;

    public void fade(View view) {
        Log.i("Info", "Imageview berührt");
        ImageView bart = findViewById(R.id.imageViewBart);
        // ImageView homer = findViewById(R.id.imageViewHomer);

        bart.animate().scaleX(.5f).scaleY(0.5f).setDuration(2500);


        // IGNORE
        /*if (bartisshowing) {
            bart.animate().rotation(200).setDuration(2000);
            bart.animate().alpha(0).setDuration(4000);
            homer.animate().alpha(1).setDuration(6000);
            bartisshowing = false;
        }
        else {
            homer.animate().alpha(0).setDuration(2000);
            bart.animate().rotation(0);
            bart.animate().alpha(1).setDuration(2000);
            bartisshowing = true;
        }*/
    }
}
