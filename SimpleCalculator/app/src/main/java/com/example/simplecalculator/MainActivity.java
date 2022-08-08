package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.number1);
        editText2 = findViewById(R.id.number2);
        textView  = findViewById(R.id.result);
    }

    private ArrayList<String> CheckInput(){
        String number1= String.valueOf(editText1.getText());
        String number2= String.valueOf(editText2.getText());

        if(!(number1.matches("")) && !(number2.matches(""))){
            ArrayList<String> result = new ArrayList<String>();
            result.add(number1);
            result.add(number2);
            return result;
        }
        return null;
    }

    public void sum(View view){
        ArrayList<String> result = CheckInput();
        if(result != null)
            textView.setText(String.valueOf(Integer.valueOf(result.get(0))+Integer.valueOf(result.get(1))));
    }

    public void deduct(View view){
        ArrayList<String> result = CheckInput();
        if(result != null)
            textView.setText(String.valueOf(Integer.valueOf(result.get(0))-Integer.valueOf(result.get(1))));
    }

    public void multiply(View view){
        ArrayList<String> result = CheckInput();
        if(result != null) {
            textView.setText(String.valueOf(Integer.valueOf(result.get(0)) * Integer.valueOf(result.get(1))));
        }
    }

    public void divide(View view){
        ArrayList<String> result = CheckInput();
        if(result != null)
            textView.setText(String.valueOf(Integer.valueOf(result.get(0))/Integer.valueOf(result.get(1))));
    }
}