package com.example.project.fiesta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class recent extends AppCompatActivity {

    Button insert;
    DatabaseReference ref,ref1;
    String uid,event1,event2;
    EditText e_name1,e_contact1,e_name2,e_contact2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        e_name1 = (EditText) findViewById(R.id.e_name1);
        e_contact1 = (EditText) findViewById(R.id.e_contact1);
       // e_name2 = (EditText) findViewById(R.id.e_name2);
       // e_contact2 = (EditText) findViewById(R.id.e_contact2);
        insert = (Button) findViewById(R.id.insert);

        try {
            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insert();
                    Intent myIntent = new Intent(recent.this,server.class);
                    startActivity(myIntent);
                }
            });
        }
        catch(Exception e){}
    }



    public void insert(){
        //Toast.makeText(recent.this, "hello", Toast.LENGTH_SHORT).show();
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        //Toast.makeText(recent.this, uid, Toast.LENGTH_SHORT).show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();



        String ename1 = e_name1.getText().toString();
        event1=event1+","+e_name1.getText().toString();


        String econtact1 = e_contact1.getText().toString();
        if(e_contact1.getText().toString().length()<10) {
            Toast.makeText(this, "plz enter the contact number ", Toast.LENGTH_SHORT).show();
            return;
        }
        event1=event1+","+e_contact1.getText().toString();


       /* String ename2 = e_name2.getText().toString();
        event1=event2+","+e_name2.getText().toString();


        String econtact2 = e_contact2.getText().toString();
        if(e_contact1.getText().toString().length()<10) {
            Toast.makeText(this, "plz enter the contact number ", Toast.LENGTH_SHORT).show();
            return;
        }
        event2=event2+","+e_contact2.getText().toString();
        //Toast.makeText(recent.this, event1, Toast.LENGTH_SHORT).show();*/
        ref = database.getReference("service_provider/"+uid);
        String key= ref.push().getKey();
        final Map service_provider = new HashMap();

        if(TextUtils.isEmpty(event1)){
            Toast.makeText(recent.this, " Not Inserted", Toast.LENGTH_SHORT).show();}
      /*  if(TextUtils.isEmpty(event2)){
            Toast.makeText(recent.this, "Not Inserted", Toast.LENGTH_SHORT).show();}*/

        service_provider.put("event_1", event1);

      //  service_provider.put("event_2", event2);
        ref.child(key).setValue(service_provider);
        Toast.makeText(recent.this,"Inserted", Toast.LENGTH_SHORT).show();

        //ref.child(uid).setValue(events);
    }
}

