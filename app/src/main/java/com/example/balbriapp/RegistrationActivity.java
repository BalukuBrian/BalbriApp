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

public class RegistrationActivity extends AppCompatActivity {

    private EditText email_reg;
    private EditText password_reg;
    private Button btnSignIn_reg ;
    private Button btnSignUp_reg ;

    /* Firebase code */
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* Firebase */
        mAuth   =  FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        /* variables */
        email_reg      =  findViewById(R.id.email_login);
        password_reg   =  findViewById(R.id.password_login);
        btnSignIn_reg  =  findViewById(R.id.btnsignin_reg);
        btnSignUp_reg  =  findViewById(R.id.btnsigup_reg);

        /* complete login */
        btnSignIn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        /* for Sign Up */
        btnSignUp_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email_reg.getText().toString().trim();
                String mPassword = password_reg.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email_reg.setError("Email Field Required.");
                    return;
                }

                if(TextUtils.isEmpty(mPassword)){
                    password_reg.setError("Email Field Required.");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            Toast.makeText(getApplicationContext(), "SignUp Complete", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Problem Signing Up", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }

                    }
                });

            }
        });


    }
}