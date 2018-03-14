package com.example.project.fiesta2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by user on 10-03-2018.
 */

public class ArtistList extends ArrayAdapter<Companies> {
        private Activity context;
        private List<Companies> artistList;

        public ArtistList(Activity context, List<Companies> artistList) {
                super(context, R.layout.list_layout,artistList);
                this.context = context;
                this.artistList = artistList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = context.getLayoutInflater();


                View listViewItem = inflater.inflate(R.layout.list_layout, null, true);


                TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
                TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
                TextView textViewBud = (TextView) listViewItem.findViewById(R.id.textViewBud);

                Companies artist = artistList.get(position);

                textViewName.setText(artist.getName());
                textViewGenre.setText(artist.getLocation());
                textViewBud.setText(artist.getMax_budget().toString());
                if(convertView!=null) {
                        convertView.setClickable(true);
                        convertView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                        // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        // String uid=user.getUid();
                                        //String str=companies.getKey();
                                        Toast.makeText(context, "Key", Toast.LENGTH_SHORT).show();
                                        //Intent intent = new Intent(context,company_display.class);
                                        //intent.putExtra("key",str);
                                        //context.startActivity(intent);
                                }
                        });
                }

                return listViewItem;
        }

}
