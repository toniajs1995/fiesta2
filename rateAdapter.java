package com.example.hp.ratingbar;

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
 * Created by navidha on 12-03-2018.
 */

public class rateAdapter extends ArrayAdapter {

    TextView RATE;
    Activity activity;
    List<rate> list;

    public rateAdapter(Activity activity, List<rate> list) {
        super(activity, R.layout.custom_layout, list);
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.custom_layout, null, true);

        RATE = (TextView) convertView.findViewById(R.id.rate_here);

        rate rr = list.get(position);
        RATE.setText(rr.getRate1());

        return convertView;
    }
}
