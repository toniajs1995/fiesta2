package com.example.project.fiesta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class rating extends AppCompatActivity {

    RatingBar ratingBar;
    TextView txtRatingValue;
    Button btnInsert;
    FirebaseDatabase mdatabase;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        final String key = getIntent().getStringExtra("key");
        ref= mdatabase.getInstance().getReference("rates/"+key);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));
                //getValues();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();
                String rate=txtRatingValue.getText().toString();
                String id = ref.push().getKey();
                rate artist=new rate(rate,email);
                ref.child(id).setValue(artist);
                Toast.makeText(rating.this,"rating saved",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
