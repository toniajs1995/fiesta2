package com.example.project.fiesta2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 26-03-2018.
 */

public class reviewAdapter extends ArrayAdapter<Artist> {
    private Context context;
    private ArrayList<Artist> company;

    private static class ViewHolder {
        TextView dname, drate;
    }

    public reviewAdapter(ArrayList<Artist> data, Context context) {
        super(context, R.layout.review_layout, data);
        this.company = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Artist companies  = company.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        reviewAdapter.ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;
        if (convertView == null) {

            viewHolder = new reviewAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.review_layout, parent, false);
            viewHolder.dname = (TextView) convertView.findViewById(R.id.email);
            viewHolder.drate = (TextView) convertView.findViewById(R.id.review);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        //Toast.makeText(reviewAdapter.this,"key", Toast.LENGTH_SHORT).show();
        viewHolder.dname.setText(companies.getArtistid());
        viewHolder.drate.setText(companies.getComments());
        return convertView;
    }
}
