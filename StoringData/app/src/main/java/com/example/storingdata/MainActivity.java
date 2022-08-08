package com.example.storingdata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        sharedPreferences = this.getSharedPreferences("com.example.storingdata", Context.MODE_PRIVATE);
        textView.setText("Hello,"+sharedPreferences.getString("Name","{Name}")+"!");
    }

    public void updateName(View view){
        textView.setText("Hello,"+editText.getText().toString()+"!");
        sharedPreferences.edit().putString("Name",editText.getText().toString()).apply();
    }

    public void clearData(View view){
        if(sharedPreferences.getString("Name","") != "") {
            sharedPreferences.edit().remove("Name").apply();
        }
    }
}