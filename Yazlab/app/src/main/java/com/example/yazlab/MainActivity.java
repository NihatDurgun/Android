package com.example.yazlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Sorgu1;
    Button Sorgu2;
    Button Sorgu3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sorgu1=findViewById(R.id.Sorgu1);
        Sorgu2=findViewById(R.id.Sorgu2);
        Sorgu3=findViewById(R.id.Sorgu3);
    }

    public void Sorgu1( View view){
        Toast.makeText(getApplicationContext(),"DENEME",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(getApplicationContext(),Sorgu1.class);
        startActivity(intent);
    }
    public void Sorgu2( View view){
        Toast.makeText(getApplicationContext(),"DENEME",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(getApplicationContext(),Sorgu2.class);
        startActivity(intent);
    }
    public void Sorgu3( View view){
        Toast.makeText(getApplicationContext(),"DENEME",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(getApplicationContext(),Sorgu3.class);
        startActivity(intent);
    }
}