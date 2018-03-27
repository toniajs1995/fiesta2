package com.example.project.fiesta2;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 2/20/2018.
 */
public class DisplayAdapter extends ArrayAdapter<Companies> {

    DatabaseReference mDatabase;
    private Context context;
    private ArrayList<Companies> company;

    private static class ViewHolder {
        TextView dname, dlic_no, dadd,dloc, ddis, dmin_bud,dmax_bud,dcat,demail,dphone,devent1,devent2;
        ImageView image;
        Button bookmark;
    }

    public DisplayAdapter(ArrayList<Companies> data, Context context) {
        super(context, R.layout.company_display, data);
        this.company = data;
        this.context = context;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Companies company  = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.company_display, parent, false);
            viewHolder.dname = (TextView) convertView.findViewById(R.id.dname);
            viewHolder.dadd = (TextView) convertView.findViewById(R.id.dadd);
            viewHolder.dloc = (TextView) convertView.findViewById(R.id.dloc);
            viewHolder.ddis = (TextView) convertView.findViewById(R.id.ddis);
            viewHolder.dmin_bud = (TextView) convertView.findViewById(R.id.dmin_bud);
            viewHolder.dmax_bud = (TextView) convertView.findViewById(R.id.dmax_bud);
            viewHolder.dcat = (TextView) convertView.findViewById(R.id.dcat);
            viewHolder.demail = (TextView) convertView.findViewById(R.id.demail);
            viewHolder.dphone = (TextView) convertView.findViewById(R.id.dphone);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.bookmark = (Button) convertView.findViewById(R.id.bookmark);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

       // String[] parts = company.getCategory().split(Pattern.quote(",")); // Split on period.
        viewHolder.dname.setText(company.getName());
        viewHolder.dadd.setText(company.getAddress());
        viewHolder.dloc.setText(company.getLocation());
        viewHolder.ddis.setText(company.getDistrict());
        viewHolder.dmin_bud.setText(company.getMin_budget());
        viewHolder.dmax_bud.setText(company.getMax_budget());
        viewHolder.dcat.setText(company.getCategory());
        viewHolder.demail.setText(company.getEmail_id());
        viewHolder.dphone.setText(company.getPhone());
        //viewHolder.image.setText(company.getImage());
        Glide.with(context).load(company.getImage()).into(viewHolder.image);
        // Return the completed view to render on screen
       viewHolder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // final String buy_qty = viewHolder.buy_quantity.getText().toString();
                //final String prd_qty = products.getQuantity();
                //double qnty = Double.parseDouble(buy_qty);
                //double prdt_price = Double.parseDouble(products.getPrice());
                //String tot_price = Double.toString(qnty*prdt_price);

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    Toast.makeText(context, "Company added to bookmarks", Toast.LENGTH_SHORT).show();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("bookmark/"+uid);
                    String id=mDatabase.push().getKey();
                    final Map bookmark = new HashMap();
                    //cart.put("key",products.getrKey());

                    bookmark.put("name",company.getName());
                    bookmark.put("image",company.getImage());
                    bookmark.put("bkey",id);
                    mDatabase.child(id).setValue(bookmark);
                    //mDatabase.child(uid).setValue(cart);

            }
        });
        return convertView;
    }
}


