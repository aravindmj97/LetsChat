package com.iamaravind.fcmapp;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChatRoom extends AppCompatActivity  {

    int i=0;
    EditText msg;
    Button send;
    Firebase chatFire, chatFire1;
    FirebaseAuth mAuth;
    ListView chatlist;
    ArrayList<String> chats = new ArrayList<>();
    String divId, msgval, key, name, ii, uid;

    @JsonIgnore
    String jsonkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        msg = (EditText)findViewById(R.id.message);
        send = (Button)findViewById(R.id.sendbtn);
        chatlist = (ListView)findViewById(R.id.chatspace);
        final ArrayAdapter<String> chatadpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chats);
        chatlist.setAdapter(chatadpt);

        Firebase.setAndroidContext(this);
        divId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        chatFire = new Firebase("https://fcmapp-38780.firebaseio.com");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String mydate = sdf.format(Calendar.getInstance().getTime());
                msgval = name+" : "+msg.getText().toString();
                ii =Integer.toString(i);
                key = mydate+ii+divId;
                i++;
                Firebase myNewChild = chatFire.child(key);
                myNewChild.setValue(msgval);
                msg.setText("");
            }
        });

        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Cname");

        Toast.makeText(ChatRoom.this, "User ID = "+uid, Toast.LENGTH_SHORT).show();

        chatFire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String childValues = dataSnapshot.getValue(String.class);
                chats.add(childValues);
                chatadpt.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                finish();
                startActivity(getIntent());
                //chatadpt.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String childValues = dataSnapshot.getValue(String.class);
                chats.remove(childValues);
                chatadpt.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
