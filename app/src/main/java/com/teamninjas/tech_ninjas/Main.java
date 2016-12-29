package com.teamninjas.tech_ninjas;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;

public class Main extends AppCompatActivity {

    ImageView image;
    TextView model;
    TextView price;
    TextView engine;
    TextView colors;
    TextView variants;

    String mImage_url ;
    String mModel ;
    String mPrice ;
    String mEngine ;
    String mColors ;
    String mVariants ;


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

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigaitonVIew);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(Main.this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);;
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //TODO ................
                return false;
            }
        });



      Retrofit_Interface retrofit_interface = Retrofit_Client.getClient().create(Retrofit_Interface.class);
        String currentQuery = "12" ;
        Call<Response> responseCall = retrofit_interface.getResponse(currentQuery) ;

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                  Response output_Response = response.body();

                    mModel = output_Response.getmModel();
                    mColors = output_Response.getmColors();
                    mEngine = output_Response.getmEngine() ;
                    mImage_url = output_Response.getmImage_Url();
                    mVariants = output_Response.getmVarients() ;
                    mPrice = output_Response.getmPrice() ;

                    Log.i("abcde" , mModel + ": " + mColors + " :" + mPrice + " : " + mEngine + " : " + mVariants + " : " + mPrice
                                +"\n : " + mImage_url
                    ) ;


                    model.setText(mModel);
                    price.setText(mPrice);
                    variants.setText(mVariants);
                    colors.setText(mColors);
                    image.setImageURI(Uri.parse(mImage_url));
                    engine.setText(mEngine);

                    Glide.with(Main.this)
                            .load(mImage_url)
                            .placeholder(R.drawable.logo)
                            .crossFade()
                            .fitCenter()
                            .into(image) ;

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                Log.i("abc" , "Error") ;

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(Main.this , LoginActivity.class);
        startActivity(i);



    }
}
