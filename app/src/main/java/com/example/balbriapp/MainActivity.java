package com.example.balbriapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnSignIn;
    private Button btnSignUp;

    /* Firebase */
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Firebase */
        mAuth =  FirebaseAuth.getInstance();

        /*This if helps a user not to log in every time*/
        if (mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }

        mDialog = new ProgressDialog(this);


        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        btnSignIn = findViewById(R.id.btnsigin);
        btnSignUp = findViewById(R.id.btnsignup);

        /* complete signing in */
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail = email.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError(" Email Field Required..");
                    return;
                }

                if(TextUtils.isEmpty(mPassword)){
                    password.setError("Password Field Required..");
                    return;
                }


                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            Toast.makeText(getApplicationContext(), "login Complete", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


        /* for SignUp */
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });


    }
}