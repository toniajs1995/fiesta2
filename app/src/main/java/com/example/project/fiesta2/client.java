package com.example.project.fiesta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class client extends AppCompatActivity {

    private ImageButton imageMarriage;
    private ImageButton imageBirthday;
    private ImageButton imageBaptism;
    private FirebaseAuth firebaseAuth;
    private Button search_budget,search_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user= firebaseAuth.getCurrentUser();

        search_budget=(Button)findViewById(R.id.search_budget);
        search_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(client.this, budget.class);
                startActivity(i);
            }
        });

        search_location=(Button)findViewById(R.id.search_location);
        search_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(client.this, location.class);
                startActivity(i);
            }
        });

        imageMarriage=(ImageButton)findViewById(R.id.imageMarriage);
        imageMarriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(client.this, marriage.class);
                startActivity(i);
            }
        });

        imageBirthday=(ImageButton)findViewById(R.id.imageBirthday);
        imageBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(CprofileActivity.this, BirthdayActivity.class);
                //startActivity(i);
                //Toast.makeText(Home.this,"View caters",Toast.LENGTH_LONG).show();
            }
        });

        imageBaptism=(ImageButton)findViewById(R.id.imageBaptism);
        imageBaptism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i = new Intent(CprofileActivity.this, BaptismActivity.class);
                //startActivity(i);
                //Toast.makeText(Home.this,"View makeups",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       // Intent intent = new Intent(server.this,profile.class);
       // startActivity(intent);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

