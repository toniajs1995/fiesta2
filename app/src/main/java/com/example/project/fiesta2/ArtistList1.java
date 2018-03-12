package com.example.project.fiesta2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 10-03-2018.
 */

public class ArtistList1 extends ArrayAdapter<Companies> {
    private Activity context;
    private List<Companies> artistList1;

    public ArtistList1(Activity context, List<Companies> artistList1) {
        super(context, R.layout.list_layout1,artistList1);
        this.context = context;
        this.artistList1 = artistList1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout1,null,true);


        TextView textViewName= (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre= (TextView)listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewBud= (TextView)listViewItem.findViewById(R.id.textViewBud);

        Companies artist =artistList1.get(position);

        textViewName.setText(artist.getName());
        textViewGenre.setText(artist.getDistrict());
        textViewBud.setText(artist.getLocation().toString());

        return listViewItem;
    }
}
