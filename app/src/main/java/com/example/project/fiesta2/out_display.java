package com.example.project.fiesta2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by user on 06-04-2018.
 */

public class out_display extends AppCompatActivity {

    private ArrayList<Companies> companies;
    DatabaseReference mDatabase;
    TextView dname, dlic_no, dadd,dloc, ddis, dmin_bud,dmax_bud,dcat,demail,dphone,devent1,devent2;
    String uid,str,key;
    ImageView image;
    DisplayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_display);
        //final String key = getIntent().getStringExtra("key");
        // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //uid = user.getUid();
        listView=(ListView)findViewById(R.id.list);
        key = getIntent().getStringExtra("key");
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
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                companies=new ArrayList<>();
                DataSnapshot snap=snapshot.child("outfit");
                // Toast.makeText(company_display.this, "Key "+key, Toast.LENGTH_SHORT).show();
                Companies company = snap.child(key).getValue(Companies.class);
                companies.add(company);
                adapter= new DisplayAdapter(companies,getApplicationContext(),"outfit",key);
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.company_display, menu);
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
            Intent intent = new Intent(out_display.this,review.class);
            intent.putExtra("key",key);
            // Toast.makeText(company_display.this, key, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings1) {
            Intent intent = new Intent(out_display.this,rating.class);
            intent.putExtra("key",key);
            //  Toast.makeText(company_display.this, key, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings2) {
            Intent intent = new Intent(out_display.this,bookmark.class);
            // intent.putExtra("key",key);
            //  Toast.makeText(company_display.this, key, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
