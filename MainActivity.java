package com.example.hp.ratingbar;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView txtRatingValue;
    Button btnInsert;
    FirebaseDatabase mdatabase;
    DatabaseReference ref;

    //rate rate();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
        btnInsert = (Button) findViewById(R.id.btnInsert);


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
                String rate=txtRatingValue.getText().toString();
                mdatabase = FirebaseDatabase.getInstance();
                ref = mdatabase.getReference("user");
                String id = ref.push().getKey();
                Map user = new HashMap();
                user.put("user3",rate);
                ref.child(id).setValue(user);
                Toast.makeText(MainActivity.this,id+" "+rate, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void addListenerOnRatingBar() {


    }

   /* private void getValues()
    {

        //  String rate1 = rate.getText().toString().trim();
       // final String value = txtRatingValue.getText().toString().trim();
        Toast.makeText(this, "rate"+value, Toast.LENGTH_LONG).show();

        Toast.makeText(this,ratingBar.toString(), Toast.LENGTH_SHORT).show();

        if (!TextUtils.isEmpty(value)) {

            String id = ref.push().getKey();
            Map user = new HashMap();
            user.put("user3",value);
            ref.child(id).setValue(user);
            Toast.makeText(this, "rate inserted", Toast.LENGTH_LONG).show();
            // startActivity(new Intent(MainActivity.this,New_Activity.class));
        } else {
            Toast.makeText(this, "you should enter a valid rate", Toast.LENGTH_LONG).show();
        }
    */

    public void btnInsert(View view){

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                //ref.child("user3").setValue(user);
                //Toast.makeText(MainActivity.this, "rate inserted", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



   /* public void addListenerOnButton() {


        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
             }

        });

    }*/

}


