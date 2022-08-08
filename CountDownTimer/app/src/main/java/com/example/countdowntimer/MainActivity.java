package com.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.timer);
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                timer.setText("Left:"+String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
            }
        }.start();
    }
}