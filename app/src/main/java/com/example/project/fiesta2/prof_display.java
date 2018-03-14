package com.example.project.fiesta2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class prof_display extends AppCompatActivity {

    TextView dname, dlic_no, dadd, dloc, ddis, dmin_bud, dmax_bud, dcat, demail, dphone, devent1, devent2,ddate;
    String uid, name, lic_no, add, loc, dis, min_bud, max_bud, cat, email, phone, event1, event2;
    ImageButton icon;
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_display);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        Toast.makeText(this, uid.toString(), Toast.LENGTH_SHORT).show();

        mdatabase = FirebaseDatabase.getInstance().getReference().child("service_provider").child(uid);
        if (uid != null) {

            dname = (TextView) findViewById(R.id.dname);
            dlic_no = (TextView) findViewById(R.id.dlic_no);
            dadd = (TextView) findViewById(R.id.dadd);
            dloc = (TextView) findViewById(R.id.dloc);
            ddis = (TextView) findViewById(R.id.ddis);
            dmin_bud = (TextView) findViewById(R.id.dmin_bud);
            dmax_bud = (TextView) findViewById(R.id.dmax_bud);
            dcat = (TextView) findViewById(R.id.dcat);
            demail = (TextView) findViewById(R.id.demail);
            dphone = (TextView) findViewById(R.id.dphone);
            devent1 = (TextView) findViewById(R.id.devent1);
            devent2 = (TextView) findViewById(R.id.devent2);
            //Toast.makeText(this,uid.toString(), Toast.LENGTH_SHORT).show();

            mdatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   // Glide.with(this).load(companies.getImage()).into(imageView);
                    name = dataSnapshot.child("name").getValue().toString();
                    lic_no = dataSnapshot.child("licence_no").getValue().toString();
                    add = dataSnapshot.child("address").getValue().toString();
                    loc = dataSnapshot.child("location").getValue().toString();
                    dis = dataSnapshot.child("district").getValue().toString();
                    cat = dataSnapshot.child("category").getValue().toString();
                    min_bud = dataSnapshot.child("min_budget").getValue().toString();
                    max_bud = dataSnapshot.child("max_budget").getValue().toString();
                    email = dataSnapshot.child("email_id").getValue().toString();
                    phone = dataSnapshot.child("phone").getValue().toString();
                    // String event1 = dataSnapshot.child("event1").getValue().toString();
                    // String event2 = dataSnapshot.child("event2").getValue().toString();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            dname.setText(name);
            dlic_no.setText(lic_no);
            dadd.setText(add);
            dloc.setText(loc);
            ddis.setText(dis);
            dcat.setText(cat);
            dmin_bud.setText(min_bud);
            dmax_bud.setText(max_bud);
            demail.setText(email);
            dphone.setText(phone);
        }

        mdatabase = FirebaseDatabase.getInstance().getReference().child("events").child(uid);
        if (uid != null) {

            devent1 = (TextView) findViewById(R.id.devent1);
            devent2 = (TextView) findViewById(R.id.devent2);
            //Toast.makeText(this,uid.toString(), Toast.LENGTH_SHORT).show();

            mdatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String event1 = dataSnapshot.child("event1").getValue().toString();
                    String event2 = dataSnapshot.child("event2").getValue().toString();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            devent1.setText(event1);
            devent2.setText(event2);
        }
        mdatabase = FirebaseDatabase.getInstance().getReference().child("dates").child(uid);
        if (uid != null) {

            ddate = (TextView) findViewById(R.id.ddate);

            mdatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String ddate = dataSnapshot.child("date").getValue().toString();
                  }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            ddate.setText(event1);
            //devent2.setText(event2);
        }


    }
}
