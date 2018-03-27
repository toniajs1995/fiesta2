package com.example.project.fiesta2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class rating_dis extends AppCompatActivity {


    private ArrayList<rate> companies;
    DatabaseReference mDatabase;

    String uid,str,key;
    ImageView image;
    rateAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_dis);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        listView=(ListView)findViewById(R.id.list);
        //key = getIntent().getStringExtra("key");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                companies=new ArrayList<>();
                DataSnapshot snap=snapshot.child("rates/"+uid);
                for (DataSnapshot post : snap.getChildren()) {
                   // Toast.makeText(rating_dis.this, "Key "+post.getKey(), Toast.LENGTH_SHORT).show();
                    rate company = post.getValue(rate.class);
                    companies.add(company);
                }
                adapter= new rateAdapter(companies,getApplicationContext());
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
