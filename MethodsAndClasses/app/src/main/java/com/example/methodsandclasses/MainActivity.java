package com.example.methodsandclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on create called!");
        makeSimpson();
    }

    public void makeSimpson(){
        Simpsons m1 = new Simpsons("Homer",50,"Nuclear");
        m1.setAge(51);
        System.out.println(m1.getAge());


    }

    public int function(){
        int x = 4+4;
        return x;
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("on resume called!");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("on stop called!");
    }


}
