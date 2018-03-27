package com.example.project.fiesta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class review_dis extends AppCompatActivity {

    private ArrayList<Artist> companies;
    DatabaseReference mDatabase;
    String uid,str,key;
    reviewAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_dis);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        listView=(ListView)findViewById(R.id.list);
        //key = getIntent().getStringExtra("key");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                companies=new ArrayList<Artist>();
                DataSnapshot snap=snapshot.child("review/"+uid);
                for (DataSnapshot post : snap.getChildren()) {
                    //Toast.makeText(review_dis.this,"key", Toast.LENGTH_SHORT).show();
                    Artist company = post.getValue(Artist.class);
                    companies.add(company);
                }
                adapter= new reviewAdapter(companies,getApplicationContext());
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
