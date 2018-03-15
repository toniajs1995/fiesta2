package com.example.abcde.comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText comments;
    Button submit;
    String id;
    DatabaseReference databaseArtist;

    ListView listViewArtists;
    List<Artist> artistList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase database=FirebaseDatabase.getInstance();

        databaseArtist= database.getInstance().getReference("artists/"+id);


        comments=(EditText)findViewById(R.id.editText);
        submit=(Button)findViewById(R.id.button2);

        listViewArtists =(ListView)findViewById(R.id.listviewArtist);
        artistList=new ArrayList<>();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArtist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Artist artist =artistSnapshot.getValue(Artist.class);

                    artistList.add(artist);
                }
                ArtistList adapter=new ArtistList(MainActivity.this,artistList);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addArtist(){


        String name= comments.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
           id= databaseArtist.push().getKey();
           Artist artist=new Artist(id,name);
           databaseArtist.child(id).setValue(artist);

            Toast.makeText(this,"review addedd",Toast.LENGTH_SHORT).show();
        }
    }

}
