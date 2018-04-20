package com.example.project.fiesta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class event_date extends AppCompatActivity {

    TextView event, date;
    DatabaseReference mdatabase,ref;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_date);

        event = (TextView) findViewById(R.id.event);
        date = (TextView) findViewById(R.id.date);
        key = getIntent().getStringExtra("key");
        //Toast.makeText(event_date.this,"key"+key, Toast.LENGTH_SHORT).show();
        mdatabase= FirebaseDatabase.getInstance().getReference("service_provider");
        ref=mdatabase.child(key);

        mdatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    Companies companies = shot.getValue(Companies.class);
                    //Toast.makeText(event_date.this,companies.getKey(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(event_date.this,"key"+key, Toast.LENGTH_SHORT).show();
                    if (companies.getKey().equals(key))
                        {
                        //for (DataSnapshot snap : shot.getChildren()) {
                          //  companies = snap.getValue(Companies.class);
                            event.setText(companies.getEvent_1());
                            date.setText(companies.getDates());
                       }
                    }
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* mdatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    Companies companies = shot.getValue(Companies.class);
                    if (companies.getKey().equals(key))
                    {
                        Toast.makeText(event_date.this, companies.getName().toString(), Toast.LENGTH_SHORT).show();
                        //event.setText(companies.getEvent_1());
                        //date.setText(companies.getDates());
                        for (DataSnapshot snap : shot.getChildren()) {
                            companies = snap.getValue(Companies.class);
                            //event.setText(companies.getEvent_1());
                            date.setText(companies.getDates());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
