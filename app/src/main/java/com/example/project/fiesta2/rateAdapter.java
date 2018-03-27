package com.example.project.fiesta2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 24-03-2018.
 */

public class rateAdapter  extends ArrayAdapter<rate> {

    private Context context;
    private ArrayList<rate> company;

    private static class ViewHolder {
        TextView dname, drate;
        RatingBar ratingBar;
    }

    public rateAdapter(ArrayList<rate> data, Context context) {
        super(context, R.layout.rate_layout, data);
        this.company = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        rate companies  = company.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        rateAdapter.ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.rate_layout, parent, false);
            viewHolder.dname = (TextView) convertView.findViewById(R.id.email);
            viewHolder.drate = (TextView) convertView.findViewById(R.id.txtRatingValue);
            viewHolder.ratingBar=(RatingBar)convertView.findViewById(R.id.ratingBar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.dname.setText(companies.getEmail());
        viewHolder.drate.setText(companies.getRate());
        viewHolder.ratingBar.setRating(Float.parseFloat(companies.getRate()));
        return convertView;
    }
}

