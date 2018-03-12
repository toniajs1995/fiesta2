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

import java.util.HashMap;
import java.util.Map;

public class dates extends AppCompatActivity {
    Button submit;
    EditText date1,date2,date3,date4,date5;
    //Date date;
    FirebaseDatabase mdatabase;
    DatabaseReference ref;
    //user user;
    String uid,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        submit = (Button) findViewById(R.id.submit);
        date1 = (EditText) findViewById(R.id.date1);
        date2 = (EditText) findViewById(R.id.date2);
        date3 = (EditText) findViewById(R.id.date3);
        date4 = (EditText) findViewById(R.id.date4);
        date5 = (EditText) findViewById(R.id.date5);
        mdatabase = FirebaseDatabase.getInstance();
        ref = mdatabase.getReference("dates");
        //user = new user();

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getValues();
                Intent myIntent = new Intent(dates.this,server.class);
                startActivity(myIntent);
            }
        });

    }

    private void getValues()
    {
        String Date = date1.getText().toString().trim();
        Date = Date+","+date2.getText().toString().trim();
        Date = Date+","+date3.getText().toString().trim();
        Date = Date+","+date4.getText().toString().trim();
        Date = Date+","+date5.getText().toString().trim();
        Toast.makeText(this,Date.toString(), Toast.LENGTH_SHORT).show();
        if (!TextUtils.isEmpty(Date)) {

            //String user2 = ref.push().getKey();
            Map dates= new HashMap();
            dates.put("date",Date);
            ref.child(uid).setValue(dates);
            Toast.makeText(this, "date inserted", Toast.LENGTH_LONG).show();
            /* startActivity(new Intent(MainActivity.this,New_Activity.class)); */
        } else {
            Toast.makeText(this, "you should enter a valid date", Toast.LENGTH_LONG).show();
        }


    }

   /* public void submit(View view){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getValues();
                ref.child("user1").setValue(user);
                Toast.makeText(MainActivity.this, "Date inserted successfully...", Toast.LENGTH_SHORT).show();


        })*/

}
