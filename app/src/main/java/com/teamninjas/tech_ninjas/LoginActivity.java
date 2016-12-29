package com.teamninjas.tech_ninjas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    EditText mPassword_Edittext_Login , mPassword_Edittext_Sinup;
    EditText mEmail_Edittext_Login , mEmail_Edittext_Sinup;
    Button mLogin , mSinup ;
    AlertDialog showSinup_Dialogue ;
    SweetAlertDialog mDialog_WhileLogin ;

    FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPassword_Edittext_Login = (EditText)findViewById(R.id.Login_passwordEditText);
        mEmail_Edittext_Login = (EditText)findViewById(R.id.Login_emailEditText);
        mLogin = (Button)findViewById(R.id.Login_LoginButton);
        mSinup = (Button)findViewById(R.id.Login_SinupButton) ;

        mAuth = FirebaseAuth.getInstance() ;


        createSinupDialogue();


        mSinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSinup_Dialogue.show();
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginToAccount(mEmail_Edittext_Login.getText().toString() , mPassword_Edittext_Login.getText().toString());
                mDialog_WhileLogin = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);;
                mDialog_WhileLogin.setTitleText("Loading ");
                mDialog_WhileLogin.getProgressHelper().setBarColor(Color.parseColor("#FF1414"));
                mDialog_WhileLogin.setCancelable(false);
                mDialog_WhileLogin.show();
            }
        });


    }

    private void loginToAccount(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email , pass)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this , "Login Success" , Toast.LENGTH_SHORT).show();
                                final SweetAlertDialog  sweetAlertDialog_Login = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                sweetAlertDialog_Login.setTitleText("SUCCESSFULL");
                                sweetAlertDialog_Login.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog_Login.dismiss();
                                    }
                                });
                                sweetAlertDialog_Login.show();
                            }

                        else if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this , "LOGIN FAILED :(" , Toast.LENGTH_SHORT).show();
                            }
                    }
                }) ;
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);



    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    //Creating SinupDialogue ..............................................................
    private void createSinupDialogue() {
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.custom_sinup_view , null);
        showSinup_Dialogue = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("REGISTER ")
                .setIcon(R.drawable.person)
                .setView(view)
                .setPositiveButton("SINUP ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                            mEmail_Edittext_Sinup = (EditText)showSinup_Dialogue.findViewById(R.id.Sinup_emailEditText) ;
                            mPassword_Edittext_Sinup = (EditText)showSinup_Dialogue.findViewById(R.id.Sinup_passwordEditText);
                           Log.i("abc" , "email -> sinup:" + mEmail_Edittext_Sinup.getText() + "Pass -> " + mPassword_Edittext_Sinup) ;

                           create_new_User( mEmail_Edittext_Sinup.getText().toString() ,  mPassword_Edittext_Sinup.getText().toString()) ;

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showSinup_Dialogue.dismiss();

                    }
                })
                .create();
    }

    private void create_new_User(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email ,pass )
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this , "Successfull" , Toast.LENGTH_SHORT).show();
                            final SweetAlertDialog  sweetAlertDialog_Login = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog_Login.setTitleText("SUCCESSFULL");
                            sweetAlertDialog_Login.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog_Login.dismiss();
                                }
                            });
                            sweetAlertDialog_Login.show();
                        }
                        else if(!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this , "Failed !! Try Again" , Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }


    //OnAuth Changes ...
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser() ;

        if(currentUser!=null){

            Toast.makeText(LoginActivity.this , "Login SuccessFull" , Toast.LENGTH_SHORT).show();
            Intent toMain = new Intent(LoginActivity.this , Main.class);
            startActivity(toMain);

            if(mDialog_WhileLogin!=null && mDialog_WhileLogin.isShowing())
             mDialog_WhileLogin.dismiss();




        }
        else if(currentUser == null){
            Toast.makeText(LoginActivity.this , "Log_out Successfull" , Toast.LENGTH_SHORT).show();

        }


    }
}
