package com.example.yazlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.SQLOutput;

public class GirisEkran extends AppCompatActivity {

    private EditText editEmail,editSifre;
    private String txtEmail,txtSifre;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_ekran);
        editEmail=findViewById(R.id.giris_yap_editEmail);
        editSifre=findViewById(R.id.giris_yap_editSifre);
        mAuth=FirebaseAuth.getInstance();

    }

    public void girisyap(View view){
        txtEmail=editEmail.getText().toString();
        txtSifre=editSifre.getText().toString();

        if(!TextUtils.isEmpty(txtEmail)&&!TextUtils.isEmpty(txtSifre)){
            mAuth.signInWithEmailAndPassword(txtEmail,txtSifre)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser=mAuth.getCurrentUser();
                            System.out.println("Kullanıcı Adı: "+mUser.getDisplayName());
                            System.out.println("Kullanıcı Email: "+mUser.getEmail());
                            System.out.println("Kullanıcı Uid: "+mUser.getUid());
                            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);


                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(GirisEkran.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(this,"Email ve Şifre boş olamaz",Toast.LENGTH_SHORT).show();
        }
    }
    public void kayitOlGit(View view){
        Intent intent= new Intent(getApplicationContext(),KayitEkran.class);
        startActivity(intent);
    }

}