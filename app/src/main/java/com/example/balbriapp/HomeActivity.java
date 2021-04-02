package com.example.balbriapp;

import android.app.AlertDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("BalbriApp");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "You clicked the Edit pen", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();

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