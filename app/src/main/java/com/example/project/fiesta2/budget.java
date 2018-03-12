package com.example.project.fiesta2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class budget extends AppCompatActivity {

    Button register;

    DatabaseReference databaseArtists;

    List<Companies> artistList;

    ListView listViewArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        databaseArtists= FirebaseDatabase.getInstance().getReference("service_provider");

        listViewArtists =(ListView)findViewById(R.id.listViewArtists);
        artistList=new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Companies artist =artistSnapshot.getValue(Companies.class);

                    artistList.add(artist);
                }
                ArtistList adapter=new ArtistList(budget.this,artistList);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    String place;


    public void optionselected(View view) {
        EditText e1=(EditText)findViewById(R.id.loc);
        place=e1.getText().toString();
        //long p = Long.parseLong(place);

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Companies artist =artistSnapshot.getValue(Companies.class);
                    if(Integer.parseInt(artist.max_budget) < Integer.parseInt(place)) {

                        artistList.add(artist);
                    }
                }
                ArtistList adapter=new ArtistList(budget.this,artistList);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
