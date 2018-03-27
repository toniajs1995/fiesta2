package com.example.project.fiesta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class review extends AppCompatActivity {

    EditText comments;
    Button submit;
    String uid,key;
    DatabaseReference databaseArtist;
    ListView listViewArtists;
    List<Artist> artistList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        
         key = getIntent().getStringExtra("key");
       // Toast.makeText(review.this, key, Toast.LENGTH_SHORT).show();
        FirebaseDatabase database=FirebaseDatabase.getInstance();

        databaseArtist= database.getInstance().getReference("review/"+key);


        comments=(EditText)findViewById(R.id.editText);
        submit=(Button)findViewById(R.id.button2);

        listViewArtists =(ListView)findViewById(R.id.listviewArtist);
        artistList2=new ArrayList<>();
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

                artistList2.clear();
                for (DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                    Artist artist =artistSnapshot.getValue(Artist.class);

                    artistList2.add(artist);

                }
                ArtistList2 adapter=new ArtistList2(review.this,artistList2);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addArtist(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        String name= comments.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            uid= databaseArtist.push().getKey();
            Artist artist=new Artist(email,name);
            databaseArtist.child(uid).setValue(artist);
            Toast.makeText(this,"review added",Toast.LENGTH_SHORT).show();
        }
    }
}
