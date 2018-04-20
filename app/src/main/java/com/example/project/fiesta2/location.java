package com.example.project.fiesta2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

public class location extends AppCompatActivity {

    Button button2;
    Spinner loc;
    DatabaseReference databaseArtists;

    List<Companies> artistList1;

    ListView listViewArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        loc=(Spinner) findViewById(R.id.loc);

        databaseArtists= FirebaseDatabase.getInstance().getReference("service_provider");

        listViewArtists =(ListView)findViewById(R.id.listViewArtists);
        artistList1=new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList1.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Companies artist = artistSnapshot.getValue(Companies.class);
                    String id= artist.getKey();
                    if(!id.equals(uid)) {
                        artistList1.add(artist);
                    }
                }
                ArtistList1 adapter=new ArtistList1(location.this,artistList1);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    String place;


    public void optionselected(View view) {

        place=loc.getSelectedItem().toString();
        //final long p = Long.parseLong(place);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList1.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Companies artist =artistSnapshot.getValue(Companies.class);
                    //assert artist != null;
                    //if((Integer.parseInt(e1.getText().toString())) >= artist.max_budget)
                    String id= artist.getKey();
                    if(!id.equals(uid)) {

                    if(artist.district.equals(place)){

                        artistList1.add(artist);
                    }}
                }

                if(artistList1.isEmpty())
                {
                    Toast.makeText(location.this, "Sorry,No Results Found", Toast.LENGTH_SHORT).show();
                }
                ArtistList1 adapter=new ArtistList1(location.this,artistList1);
                listViewArtists.setAdapter(adapter);
                listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Companies companies=artistList1.get(position);
                        String key=companies.getKey();
                        String category=companies.getCategory();
                        // String,key=companies.getKey();
               if(category.equals("decoration"))
                {

                    Intent intent = new Intent(location.this,company_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("catering"))
                {
                    Intent intent = new Intent(location.this,cat_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("hair_makeup"))
                {
                    Intent intent = new Intent(location.this,make_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("media"))
                {
                    Intent intent = new Intent(location.this,med_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("transportation"))
                {
                    Intent intent = new Intent(location.this,trans_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("miscellaneous"))
                {
                    Intent intent = new Intent(location.this,mis_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("entertainment"))
                {
                    Intent intent = new Intent(location.this,enter_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("outfit"))
                {
                    Intent intent = new Intent(location.this,out_display.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
                else if(category.equals("cakes_desserts")) {
                   Intent intent = new Intent(location.this, cake_display.class);
                   intent.putExtra("key",key);
                   startActivity(intent);
                }


                        //Toast.makeText(location.this,key, Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(location.this,company_display.class);
                        //intent.putExtra("key",key);
                        //startActivity(intent);
                    }

                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
