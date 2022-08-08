package com.example.yazlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Sorgu2 extends AppCompatActivity {

    Button BackButton;
    EditText pozX,pozY;
    EditText startDate,endDate;
    Button calc;
    TextView result;
    ArrayList<String> foundedIds1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorgu2);

        foundedIds1 = new ArrayList<String>();
        BackButton=findViewById(R.id.BackButton);
        pozX = findViewById(R.id.startX);
        pozY = findViewById(R.id.startY);
        startDate = findViewById(R.id.date);
        endDate = findViewById(R.id.endDate);
        calc = findViewById(R.id.calculate);
        result = findViewById(R.id.result);
    }

    public void queryCalc (View v){
        //foundedIds1.clear();
        calc.setEnabled(false);
        try {
            long startD = getTimestamp(startDate.getText().toString());
            long endD = getTimestamp(endDate.getText().toString());
            query21(startD);
            query22(endD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
        return date;
    }

    private Long getTimestamp(String str_date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = (Date)formatter.parse(str_date);
        return Long.parseLong(String.valueOf(date.getTime())) / 1000;
    }

    public void query21(long startD){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> yazlab =  db.collection("yazlab22")
                .whereGreaterThan("tpep_pickup_datetime",String.valueOf(startD))
                .whereEqualTo("DOLocationID",pozX.getText().toString())
                .whereEqualTo("PULocationID",pozY.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for(QueryDocumentSnapshot doc:task.getResult()){
                            foundedIds1.add(doc.getId().toString());
                        }
                    }
                });
    }

    public void query22(long endD){
        int[] total = {0};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //System.out.println("StartD:"+startD);
        Task<QuerySnapshot> yazlab =  db.collection("yazlab22")
                .whereLessThan("tpep_dropoff_datetime",String.valueOf(endD))
                .whereEqualTo("DOLocationID",pozX.getText().toString())
                .whereEqualTo("PULocationID",pozY.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for(QueryDocumentSnapshot doc:task.getResult()){
                            String search = doc.getId().toString();

                            for(int i=0;i< foundedIds1.size();i++){
                                System.out.println(foundedIds1.get(i)+" - "+search);
                                if(foundedIds1.get(i).equals(search)){
                                    total[0]++;
                                }
                            }
                        }
                        System.out.println("Total:"+ total[0]);
                        result.setText(String.valueOf(total[0]));
                    }
                });
    }


    public void BackButton( View view){
        Toast.makeText(getApplicationContext(),"DENEME",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}