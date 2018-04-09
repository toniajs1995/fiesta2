package com.example.project.fiesta2;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
/**
 * Created by Lenovo on 2/17/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<Companies> company;
    DatabaseReference ref,del;

    public Adapter(Context context, List<Companies> company) {
        this.company = company;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Companies companies = company.get(position);
        holder.textViewName.setText(companies.getName());
        //holder.textViewPrice.setText(companies.getLocation());

        Glide.with(context).load(companies.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category=companies.getCategory();
                String str=companies.getKey();
                if(category.equals("decoration"))
                {

                    Intent intent = new Intent(context,company_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("catering"))
                {
                    Intent intent = new Intent(context,cat_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("hair_makeup"))
                {
                    Intent intent = new Intent(context,make_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("media"))
                {
                    Intent intent = new Intent(context,med_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("transportation"))
                {
                    Intent intent = new Intent(context,trans_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("miscellaneous"))
                {
                    Intent intent = new Intent(context,mis_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("entertainment"))
                {
                    Intent intent = new Intent(context,enter_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("outfit"))
                {
                    Intent intent = new Intent(context,out_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
                else if(category.equals("cakes_desserts"))
                {
                    Intent intent = new Intent(context,cake_display.class);
                    intent.putExtra("key",str);
                    context.startActivity(intent);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String bkey=companies.getBkey();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String uid = user.getUid();
                ref = FirebaseDatabase.getInstance().getReference().child("bookmark").child(uid).child(bkey);
                ref.removeValue();
                company.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,company.size());



            }
        });
    }

    @Override
    public int getItemCount() {
        return company.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public ImageView imageView;
        public ImageButton delete;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            delete=itemView.findViewById(R.id.delete);
            itemView.setClickable(true);

        }

    }
}