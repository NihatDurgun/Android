package com.example.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("Name"));

    }

    public void changeActivity(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}