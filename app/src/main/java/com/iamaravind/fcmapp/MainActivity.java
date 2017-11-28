package com.iamaravind.fcmapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    EditText myName;
    Button submit;
    String nameChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myName = (EditText)findViewById(R.id.nametext);

        submit = (Button)findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameChat = myName.getText().toString();
                Intent nametochat = new Intent(MainActivity.this,ChatRoom.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putString("Cname", nameChat);
                nametochat.putExtras(bundle);
                MainActivity.this.startActivity(nametochat);
            }
        });

     /*   SharedPreferences prefs = this.getSharedPreferences(
                "com.iamaravind.fcmapp", Context.MODE_PRIVATE);
        boolean hasVisited = prefs.getBoolean("HAS_VISISTED_BEFORE", false);
        if(!hasVisited) {
            prefs.edit().putBoolean("HAS_VISISTED_BEFORE", true).commit();
        }
        else {
            Intent nametochat = new Intent(MainActivity.this,ChatRoom.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle bundle = new Bundle();
            bundle.putString("Cname", nameChat);
            nametochat.putExtras(bundle);
            MainActivity.this.startActivity(nametochat);
        }*/

    }
}
