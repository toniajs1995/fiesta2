package com.example.project.fiesta2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class deco extends AppCompatActivity {

    //recyclerview object
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<Companies> company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deco);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(this);

        company = new ArrayList<>();

        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("decoration");

        //adding an event listener to fetch values
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                //dismissing the progress dialog
                progressDialog.dismiss();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Companies companies = postSnapshot.getValue( Companies.class);
                    company.add(companies);

                }
                //creating adapter
                adapter = new MyAdapter(getApplicationContext(), company);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onChildChanged(DataSnapshot snapshot,String s)
            {

            }
            @Override
            public void onChildRemoved(DataSnapshot snapshot)
            {

            }
            @Override
            public void onChildMoved(DataSnapshot snapshot,String s)
            {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}

