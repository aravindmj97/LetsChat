package com.iamaravind.fcmapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailtext,pwd;
    Button sup, log;
    String mEmail, mPass;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailtext = (EditText)findViewById(R.id.email);
        pwd = (EditText)findViewById(R.id.passwd);

        sup = (Button)findViewById(R.id.signup);
        log = (Button)findViewById(R.id.login);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmail = emailtext.getText().toString();
                mPass = pwd.getText().toString();
                if(mEmail.isEmpty()||mPass.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Enter Details",Toast.LENGTH_SHORT).show();
                }
                else {
                    LoginUser();
                }

            }
        });

        mAuth = FirebaseAuth.getInstance();


        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmail = emailtext.getText().toString();
                mPass = pwd.getText().toString();
                if(mEmail.isEmpty()||mPass.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Enter Details",Toast.LENGTH_SHORT).show();
                }
                else {
                    CreateNewUser();
                }
            }
        });
    }
    private void CreateNewUser(){
        mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent toMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(toMain);
                }
                else {
                    Toast.makeText(LoginActivity.this,"SignUp Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void LoginUser(){
        mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent toMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(toMain);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
