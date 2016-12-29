package com.teamninjas.tech_ninjas;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    ImageView image;
    TextView model;
    TextView price;
    TextView engine;
    TextView colors;
    TextView variants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.main_image);
        model = (TextView) findViewById(R.id.main_model);
        price = (TextView) findViewById(R.id.main_price);
        engine = (TextView) findViewById(R.id.main_engine);
        colors = (TextView) findViewById(R.id.main_colors);
        variants = (TextView) findViewById(R.id.main_variants);



    }
}
