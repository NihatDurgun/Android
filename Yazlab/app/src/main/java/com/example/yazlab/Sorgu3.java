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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Sorgu3 extends AppCompatActivity {

    Button BackButton;
    EditText date;
    Button calc;
    TextView result;

    String DOLoc ;
    String PULoc ;
    String Distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorgu2);

        BackButton=findViewById(R.id.BackButton);
        date = findViewById(R.id.date);
        calc = findViewById(R.id.calculate);
        result = findViewById(R.id.result);
    }

    public void queryCalc (View v){
        calc.setEnabled(false);
        try {
            long startD = getTimestamp(date.getText().toString());
            query21(startD);
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
                .orderBy("trip_distance", Query.Direction.DESCENDING)
                .whereGreaterThan("tpep_pickup_datetime",String.valueOf(startD))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot doc:task.getResult()){
                            DOLoc = doc.get("DOLocationID").toString();
                            PULoc = doc.get("PULocationID").toString();
                            Distance = doc.get("trip_distance").toString();
                        }
                    }
                });
    }


    public void BackButton( View view){
        Toast.makeText(getApplicationContext(),"DENEME",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}