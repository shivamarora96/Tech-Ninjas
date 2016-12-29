package com.teamninjas.tech_ninjas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    EditText mPasswordEdittext ;
    EditText mEmailEdittext ;
    Button mLogin , mSinup ;
    AlertDialog showSinup_Dialogue ;

    FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPasswordEdittext = (EditText)findViewById(R.id.Login_passwordEditText);
        mEmailEdittext = (EditText)findViewById(R.id.Login_emailEditText);
        mLogin = (Button)findViewById(R.id.Login_LoginButton);
        mSinup = (Button)findViewById(R.id.Login_SinupButton) ;

        mAuth = FirebaseAuth.getInstance() ;
        showSinup_Dialogue = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("REGISTER ")
                .setIcon(R.drawable.ic_person)
                .setView(R.layout.custom_sinup_view)
                .setPositiveButton("SINUP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();


        mSinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    //OnAuth Changes ...
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }
}
