package com.example.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button startBtn,stopBtn;
    TextView textView;
    Runnable runnable;
    Handler handler;

    int timer=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.start);
        stopBtn = findViewById(R.id.stop);
        textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(timer));
    }

    public void start(View view) {
        startBtn.setEnabled(false);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(timer));
                timer = timer+1;
                System.out.println(timer);
                handler.postDelayed(runnable,1000);
            }
        };

        handler.post(runnable);
    }

    public void stop(View view){
        startBtn.setEnabled(true);

        handler.removeCallbacks(runnable);
        timer = 0;
        textView.setText(String.valueOf(timer));
    }
}