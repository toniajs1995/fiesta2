package com.example.project.fiesta2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by Lenovo on 2/17/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Companies> company;

    public MyAdapter(Context context, List<Companies> company) {
        this.company = company;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Companies companies = company.get(position);
        holder.textViewName.setText(companies.getName());
       // holder.textViewPrice.setText(companies.getPrice());
        Glide.with(context).load(companies.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
               // String uid=user.getUid();
                String str=companies.getKey();
               // Toast.makeText(context, "Key" + str, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,company_display.class);
                intent.putExtra("key",str);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return company.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textViewPrice;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
           // textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setClickable(true);
            }

        }
    }