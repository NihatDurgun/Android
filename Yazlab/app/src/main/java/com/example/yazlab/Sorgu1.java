package com.example.yazlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Locale;

public class Sorgu1 extends AppCompatActivity {

    Button BackButton;
    TextView res1;
    TextView res2;
    TextView res3;
    TextView res4;
    TextView res5;
    TextView totalRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorgu1);

        BackButton=findViewById(R.id.BackButton);
        res1=findViewById(R.id.result1);
        res2=findViewById(R.id.result2);
        res3=findViewById(R.id.result3);
        res4=findViewById(R.id.result4);
        res5=findViewById(R.id.result5);
        totalRes=findViewById(R.id.totalResult);
        query1();
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
        return date;
    }

    public void query1(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> yazlab =  db.collection("yazlab22").orderBy("passenger_count", Query.Direction.DESCENDING).limit(5).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                      int i=1;
                      int totalPass =0;
                      for(QueryDocumentSnapshot doc:task.getResult()){
                          int PassCount = Integer.valueOf(doc.get("passenger_count").toString());
                          totalPass += PassCount;
                          String NE_PickupDate =  doc.get("tpep_pickup_datetime").toString();
                          String NE_DropoffDate =  doc.get("tpep_pickup_datetime").toString();

                          String PickupDate = getDate(Long.parseLong(NE_PickupDate));
                          String DropoffDate = getDate(Long.parseLong(NE_DropoffDate));

                          switch (i) {
                              case 1:
                                  res1.setText(PickupDate + " - " + DropoffDate + ":" + PassCount);
                              break;
                              case 2:
                                  res2.setText(PickupDate + " - " + DropoffDate + ":" + PassCount);
                              break;
                              case 3:
                                  res3.setText(PickupDate + " - " + DropoffDate + ":" + PassCount);
                              break;
                              case 4:
                                  res4.setText(PickupDate + " - " + DropoffDate + ":" + PassCount);
                              break;
                              case 5:
                                  res5.setText(PickupDate + "-" + DropoffDate + ":" + PassCount);
                              break;
                          }
                          i++;
                      }
                        totalRes.setText(String.valueOf(totalPass));
                    }
                });
    }

    public void BackButton( View view){
        Toast.makeText(getApplicationContext(),"DENEME",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}