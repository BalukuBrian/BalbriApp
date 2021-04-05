package com.example.balbriapp;

import android.app.AlertDialog;
import android.os.Bundle;

import com.example.balbriapp.Model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("BalbriApp");


        //firebase database connection
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uid = mUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("All Data").child(uid);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData();
            }
        });
    }

    private void AddData(){
        
        AlertDialog.Builder mydialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View myview = inflater.inflate(R.layout.inputlayout, null);

        mydialog.setView(myview);
        AlertDialog dialog = mydialog.create();

        dialog.setCancelable(false);
        EditText name = myview.findViewById(R.id.name);
        EditText description = myview.findViewById(R.id.description);

        Button btnCancel= myview.findViewById(R.id.button_cancel);
        Button btnSave= myview.findViewById(R.id.button_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String mName  = name.getText().toString().trim();
                String mDescription  = description.getText().toString().trim();

                if(TextUtils.isEmpty(mName)){
                    name.setError("Name Required Field..");
                }

                if(TextUtils.isEmpty(mName)){
                    description.setError("Description Required Field..");
                }

                //getting the id
                String id = mDatabase.push().getKey();

                //getting the date
                String mDate = DateFormat.getDateInstance().format(new Data());


                Data data = new Data(mName, mDescription, id, mDate);

                // sending data to database
                mDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(), "Data Uploaded", Toast.LENGTH_SHORT).show();


                
                dialog.dismiss();

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}