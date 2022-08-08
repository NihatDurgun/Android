package com.example.yazlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitEkran extends AppCompatActivity {
    private EditText editEmail,editSifre;
    private String txtEmail,txtSifre;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekran);
        editEmail=findViewById(R.id.kayit_ol_editEmail);
        editSifre=findViewById(R.id.kayit_ol_editSifre);
        mAuth=FirebaseAuth.getInstance();
    }
    public void kayitOl(View view){
        txtEmail=editEmail.getText().toString();
        txtSifre=editSifre.getText().toString();

        if(!TextUtils.isEmpty(txtEmail)&&!TextUtils.isEmpty(txtSifre)){
            mAuth.createUserWithEmailAndPassword(txtEmail,txtSifre)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(KayitEkran.this,"Kayıt işlemi başarılı",Toast.LENGTH_SHORT).show();
                                 Intent intent= new Intent(getApplicationContext(),GirisEkran.class);
                                 startActivity(intent);
                             }
                             else{
                                 System.out.println("HATA");
                             }
                        }
                    });
        }
        else{
            Toast.makeText(this,"Email ve Şifre boş olamaz",Toast.LENGTH_SHORT).show();
        }
    }
}