package com.example.project.fiesta2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.List;

public class prof_display extends AppCompatActivity {

    TextView dname, dlic_no, dadd, dloc, ddis, dmin_bud, dmax_bud, dcat, demail, dphone, devent1, devent2,ddate;
    String uid, name, lic_no, add, loc, dis, min_bud, max_bud, cat, email, phone, event1, event2;
    Button update;
    ImageView image;
    DatabaseReference mdatabase,ref;
    FirebaseDatabase db;
    List<Companies> company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_display);

        update=(Button)findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(prof_display.this, update.class);
                startActivity(i);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        //Toast.makeText(this, uid.toString(), Toast.LENGTH_SHORT).show();
        company=new ArrayList<>();
        mdatabase=FirebaseDatabase.getInstance().getReference("service_provider");
        ref=mdatabase.child(uid);

        if (uid != null) {

            dname = (TextView) findViewById(R.id.dname);
          //  dlic_no = (TextView) findViewById(R.id.dlic_no);
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
            image=findViewById(R.id.image);
            //Toast.makeText(this,uid.toString(), Toast.LENGTH_SHORT).show();

            mdatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot shot : dataSnapshot.getChildren()) {
                        Companies companies = shot.getValue(Companies.class);

                        if (companies.getKey().equals(uid)) {
                            //Toast.makeText(prof_display.this, companies.getName().toString(), Toast.LENGTH_SHORT).show();
                            dname.setText(companies.getName());
                            dadd.setText(companies.getAddress());
                            dloc.setText(companies.getLocation());
                            ddis.setText(companies.getDistrict());
                            dcat.setText(companies.getCategory());
                            dmin_bud.setText(companies.getMin_budget());
                            dmax_bud.setText(companies.getMax_budget());
                            demail.setText(companies.getEmail_id());
                            dphone.setText(companies.getPhone());
                            Glide.with(prof_display.this).load(companies.getImage()).into(image);

                        }
                        }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.server, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(prof_display.this,review_dis.class);
            //intent.putExtra("key",key);
            // Toast.makeText(company_display.this, key, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings1) {
            Intent intent = new Intent(prof_display.this,rating_dis.class);
           // intent.putExtra("key",key);
            //  Toast.makeText(company_display.this, key, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
