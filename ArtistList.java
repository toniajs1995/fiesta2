package com.example.abcde.myapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ABCDE on 03-02-2018.
 */

public class ArtistList extends ArrayAdapter<eventmngr> {
    private Activity context;
    private List<eventmngr> artistList;

    public ArtistList(Activity context, List<eventmngr> artistList) {
        super(context, R.layout.list_layout,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);


        TextView textViewName= (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre= (TextView)listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewBud= (TextView)listViewItem.findViewById(R.id.textViewBud);

        eventmngr artist =artistList.get(position);

        textViewName.setText(artist.getEname());
        textViewGenre.setText(artist.getEloc());
        textViewBud.setText(artist.getBudget().toString());

        return listViewItem;
    }
}
