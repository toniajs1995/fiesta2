package com.example.hp.ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    DatabaseReference mdatabase;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mdatabase= FirebaseDatabase.getInstance().getReference().child("user/-L7j2TvgpKnoX_ow2P-P");
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String rating =dataSnapshot.child("user3").getValue().toString();
                Float rate=Float.parseFloat(rating);
                ratingBar.setRating(rate);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
