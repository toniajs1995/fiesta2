package com.example.project.fiesta2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class company_display extends AppCompatActivity {

    private ArrayList<Companies> companies;
    DatabaseReference mDatabase;
    TextView dname, dlic_no, dadd,dloc, ddis, dmin_bud,dmax_bud,dcat,demail,dphone,devent1,devent2;
    String uid;
    ImageView image;
    DisplayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_display);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        listView=(ListView)findViewById(R.id.list);
        // final String key = getIntent().getStringExtra("key");
        dname = (TextView) findViewById(R.id.dname);
        //dlic_no = (TextView) findViewById(R.id.dlic_no);
        dadd = (TextView) findViewById(R.id.dadd);
        dloc = (TextView) findViewById(R.id.dloc);
        ddis = (TextView) findViewById(R.id.ddis);
        dmin_bud = (TextView) findViewById(R.id.dmin_bud);
        dmax_bud = (TextView) findViewById(R.id.dmax_bud);
        dcat = (TextView) findViewById(R.id.dcat);
        demail = (TextView) findViewById(R.id.demail);
        dphone = (TextView) findViewById(R.id.dphone);
        // devent1 = (TextView) findViewById(R.id.devent1);
        // devent2 = (TextView) findViewById(R.id.devent2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                companies=new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //String Key=postSnapshot.getKey();
                    //if(Key==key)
                    {
                        Toast.makeText(company_display.this, "Key"+postSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                        Companies company = postSnapshot.child("decoration").child(uid).getValue(Companies.class);
                        companies.add(company);
                    }



                }
                adapter= new DisplayAdapter(companies,getApplicationContext());
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
