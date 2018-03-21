package com.example.project.fiesta2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by user on 19-03-2018.
 */

public class ArtistList2 extends ArrayAdapter<Artist> {

    private Activity context;
    private List<Artist> artistList2;

    public ArtistList2(Activity context, List<Artist> artistList3) {
        super(context, R.layout.listlayout2, artistList3);
        this.context = context;
        this.artistList2= artistList3;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();




        View listViewItem = inflater.inflate(R.layout.listlayout2,null,true);

        TextView textViewEmail=(TextView)listViewItem.findViewById(R.id.textView1);
        TextView textViewName= (TextView)listViewItem.findViewById(R.id.textView2);


        Artist artist =artistList2.get(position);

        textViewEmail.setText(artist.getArtistid());
        textViewName.setText(artist.getComments());


        return listViewItem;
    }
}
