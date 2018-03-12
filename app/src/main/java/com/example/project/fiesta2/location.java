package com.example.project.fiesta2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

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

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList1.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Companies artist = artistSnapshot.getValue(Companies.class);

                    artistList1.add(artist);
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

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList1.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Companies artist =artistSnapshot.getValue(Companies.class);
                    //assert artist != null;
                    //if((Integer.parseInt(e1.getText().toString())) >= artist.max_budget)
                    if(artist.district.equals(place)){

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
}
