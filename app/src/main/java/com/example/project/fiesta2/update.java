package com.example.project.fiesta2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class update extends AppCompatActivity {

    EditText dname;
    EditText dlic_no;
    EditText dadd;
    EditText dloc;
    Spinner ddis;
    EditText dmin_bud;
    EditText dmax_bud;
    EditText dcat;
    EditText demail;
    EditText dphone;
    EditText devent1;
    EditText devent2;
    EditText ddate;
    EditText name;
    EditText add;
    EditText loc;
    EditText min_bud;
    EditText max_bud;
    EditText cat;
    EditText email;
    EditText phone;
    String event1,event2,sid,scategory,slic,sdistrict,slocation,sminbudget,smaxbudget,semail,sphone,uid,lic_no;
    Button update,choosebtn,uploadbtn;

    DatabaseReference mdatabase,ref;
    FirebaseDatabase db;
    List<Companies> company;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //image choose
        choosebtn = (Button) findViewById(R.id.s_btnChoose);

        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseimage();
            }
        });
        //upload code
        uploadbtn = (Button) findViewById(R.id.s_btn);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        //Toast.makeText(this, uid.toString(), Toast.LENGTH_SHORT).show();
        company=new ArrayList<>();
        mdatabase= FirebaseDatabase.getInstance().getReference("service_provider");
        ref=mdatabase.child(uid);

        if (uid != null) {

            dname = (EditText) findViewById(R.id.s_name);
            dlic_no = (EditText) findViewById(R.id.s_lic);
            dadd = (EditText) findViewById(R.id.s_add);
            dloc = (EditText) findViewById(R.id.s_location);
            ddis = (Spinner) findViewById(R.id.s_district);
            dmin_bud = (EditText) findViewById(R.id.s_minbudget);
            dmax_bud = (EditText) findViewById(R.id.s_maxbudget);
            //cat = (EditText) findViewById(R.id.s_category);
            demail = (EditText) findViewById(R.id.s_email);
            dphone = (EditText) findViewById(R.id.s_phone);
            //event1 = (EditText) findViewById(R.id.);
            //event2 = (EditText) findViewById(R.id.devent2);
            //image=findViewById(R.id.image);
            //Toast.makeText(this,uid.toString(), Toast.LENGTH_SHORT).show();

            mdatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot shot : dataSnapshot.getChildren()) {
                        Companies companies = shot.getValue(Companies.class);

                        if (companies.getKey().equals(uid)) {
                            Toast.makeText(update.this, companies.getName().toString(), Toast.LENGTH_SHORT).show();
                            dname.setText(companies.getName());
                            dadd.setText(companies.getAddress());
                            dloc.setText(companies.getLocation());
                            dlic_no.setText(companies.getDistrict());
                            //dcat.setText(companies.getCategory());
                            dmin_bud.setText(companies.getMin_budget());
                            dmax_bud.setText(companies.getMax_budget());
                            demail.setText(companies.getEmail_id());
                            dphone.setText(companies.getPhone());
                            //Glide.with(update.this).load(companies.getImage()).into(image);

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    public void chooseimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();

        }
    }

    private void uploadImage() {

        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        // ref = database.getReference("service_provider");
        if(filePath != null)
        {


            final FirebaseDatabase database = FirebaseDatabase.getInstance();

            EditText s_name = (EditText) findViewById(R.id.s_name);
            String sname = s_name.getText().toString();

            EditText s_add = (EditText) findViewById(R.id.s_add);
            String sadd = s_add.getText().toString();

            CheckBox deco = (CheckBox) findViewById(R.id.deco);
            if(deco.isChecked())
            {scategory= scategory+","+deco.getText().toString();
                ref = database.getReference("decoration");
                Map decoration = new HashMap();
                decoration.put("name",sname);
                decoration.put("key",uid);
                decoration.put("licence_no", slic);
                decoration.put("address", sadd);
                decoration.put("district", sdistrict);
                decoration.put("category", scategory);
                decoration.put("min_budget", sminbudget);
                decoration.put("max_budget", smaxbudget);
                decoration.put("location", slocation);
                decoration.put("email_id", semail);
                decoration.put("phone", sphone);
                // uid = ref.push().getKey();
                ref.child(uid).setValue(decoration);
            }

            CheckBox make = (CheckBox) findViewById(R.id.make);
            if(make.isChecked())
            {scategory= scategory+","+make.getText().toString();
                ref = database.getReference("hair_makeup");
                Map hair_makeup = new HashMap();
                hair_makeup.put("name",sname);
                hair_makeup.put("key",uid);
                hair_makeup.put("licence_no", slic);
                hair_makeup.put("address", sadd);
                hair_makeup.put("district", sdistrict);
                hair_makeup.put("category", scategory);
                hair_makeup.put("min_budget", sminbudget);
                hair_makeup.put("max_budget", smaxbudget);
                hair_makeup.put("location", slocation);
                hair_makeup.put("email_id", semail);
                hair_makeup.put("phone", sphone);
                // uid = ref.push().getKey();
                ref.child(uid).setValue(hair_makeup);
            }

            CheckBox out = (CheckBox) findViewById(R.id.out);
            if(out.isChecked())
            {scategory= scategory+","+out.getText().toString();
                ref = database.getReference("outfit");
                Map outfit = new HashMap();
                outfit.put("name",sname);
                outfit.put("key",uid);
                outfit.put("licence_no", slic);
                outfit.put("address", sadd);
                outfit.put("district", sdistrict);
                outfit.put("category", scategory);
                outfit.put("min_budget", sminbudget);
                outfit.put("max_budget", smaxbudget);
                outfit.put("location", slocation);
                outfit.put("email_id", semail);
                outfit.put("phone", sphone);
                //uid = ref.push().getKey();
                ref.child(uid).setValue(outfit);
            }

            CheckBox enter = (CheckBox) findViewById(R.id.enter);
            if(enter.isChecked())
            {scategory= scategory+","+enter.getText().toString();
                ref = database.getReference("entertainment");
                Map entertainment = new HashMap();
                entertainment.put("name",sname);
                entertainment.put("key",uid);
                entertainment.put("licence_no", slic);
                entertainment.put("address", sadd);
                entertainment.put("district", sdistrict);
                entertainment.put("category", scategory);
                entertainment.put("min_budget", sminbudget);
                entertainment.put("max_budget", smaxbudget);
                entertainment.put("location", slocation);
                entertainment.put("email_id", semail);
                entertainment.put("phone", sphone);
                //uid = ref.push().getKey();
                ref.child(uid).setValue(entertainment);
            }

            CheckBox cat = (CheckBox) findViewById(R.id.cat);
            if(cat.isChecked())
            {scategory= scategory+","+cat.getText().toString();
                ref = database.getReference("catering");
                Map catering = new HashMap();
                catering.put("name",sname);
                catering.put("key",uid);
                catering.put("licence_no", slic);
                catering.put("address", sadd);
                catering.put("district", sdistrict);
                catering.put("category", scategory);
                catering.put("min_budget", sminbudget);
                catering.put("max_budget", smaxbudget);
                catering.put("location", slocation);
                catering.put("email_id", semail);
                catering.put("phone", sphone);
                //uid = ref.push().getKey();
                ref.child(uid).setValue(catering);
            }

            CheckBox trans = (CheckBox) findViewById(R.id.trans);
            if(trans.isChecked())
            {scategory= scategory+","+trans.getText().toString();
                ref = database.getReference("transportation");
                Map transportation = new HashMap();
                transportation.put("name",sname);
                transportation.put("key",uid);
                transportation.put("licence_no", slic);
                transportation.put("address", sadd);
                transportation.put("district", sdistrict);
                transportation.put("category", scategory);
                transportation.put("min_budget", sminbudget);
                transportation.put("max_budget", smaxbudget);
                transportation.put("location", slocation);
                transportation.put("email_id", semail);
                transportation.put("phone", sphone);
                //uid = ref.push().getKey();
                ref.child(uid).setValue(transportation);
            }

            CheckBox invi = (CheckBox) findViewById(R.id.invi);
            if(invi.isChecked())
            {scategory= scategory+","+invi.getText().toString();
                ref = database.getReference("invitation");
                Map invitation = new HashMap();
                invitation.put("name",sname);
                invitation.put("key",uid);
                invitation.put("licence_no", slic);
                invitation.put("address", sadd);
                invitation.put("district", sdistrict);
                invitation.put("category", scategory);
                invitation.put("min_budget", sminbudget);
                invitation.put("max_budget", smaxbudget);
                invitation.put("location", slocation);
                invitation.put("email_id", semail);
                invitation.put("phone", sphone);
                // uid = ref.push().getKey();
                ref.child(uid).setValue(invitation);
            }

            CheckBox med = (CheckBox) findViewById(R.id.med);
            if(med.isChecked())
            {scategory= scategory+","+med.getText().toString();
                ref = database.getReference("media");
                Map media = new HashMap();
                media.put("name",sname);
                media.put("key",uid);
                media.put("licence_no", slic);
                media.put("address", sadd);
                media.put("district", sdistrict);
                media.put("category", scategory);
                media.put("min_budget", sminbudget);
                media.put("max_budget", smaxbudget);
                media.put("location", slocation);
                media.put("email_id", semail);
                media.put("phone", sphone);
                // uid = ref.push().getKey();
                ref.child(uid).setValue(media);
            }

            CheckBox cake = (CheckBox) findViewById(R.id.cake);
            if(cake.isChecked())
            {scategory= scategory+","+cake.getText().toString();
                ref = database.getReference("cakes_desserts");
                Map cakes_desserts = new HashMap();
                cakes_desserts.put("name",sname);
                cakes_desserts.put("key",uid);
                cakes_desserts.put("licence_no", slic);
                cakes_desserts.put("address", sadd);
                cakes_desserts.put("district", sdistrict);
                cakes_desserts.put("category", scategory);
                cakes_desserts.put("min_budget", sminbudget);
                cakes_desserts.put("max_budget", smaxbudget);
                cakes_desserts.put("location", slocation);
                cakes_desserts.put("email_id", semail);
                cakes_desserts.put("phone", sphone);
                // uid = ref.push().getKey();
                ref.child(uid).setValue(cakes_desserts);
            }

            CheckBox mis = (CheckBox) findViewById(R.id.mis);
            if(mis.isChecked())
            {scategory= scategory+","+mis.getText().toString();
                ref = database.getReference("miscellaneous");
                Map miscellaneous = new HashMap();
                miscellaneous.put("name",sname);
                miscellaneous.put("key",uid);
                miscellaneous.put("licence_no", slic);
                miscellaneous.put("address", sadd);
                miscellaneous.put("district", sdistrict);
                miscellaneous.put("category", scategory);
                miscellaneous.put("min_budget", sminbudget);
                miscellaneous.put("max_budget", smaxbudget);
                miscellaneous.put("location", slocation);
                miscellaneous.put("email_id", semail);
                miscellaneous.put("phone", sphone);
                // uid = ref.push().getKey();
                ref.child(uid).setValue(miscellaneous);
            }

            EditText s_minbudget = (EditText) findViewById(R.id.s_minbudget);
            sminbudget = s_minbudget.getText().toString();

            EditText s_maxbudget = (EditText) findViewById(R.id.s_maxbudget);
            smaxbudget = s_maxbudget.getText().toString();

            Spinner s_district = (Spinner) findViewById(R.id.s_district);
            sdistrict = s_district.getSelectedItem().toString();

            EditText s_lic = (EditText) findViewById(R.id.s_lic );
            slic  = s_lic .getText().toString();

            EditText s_location = (EditText) findViewById(R.id.s_location);
            slocation = s_location.getText().toString();

            EditText s_email = (EditText) findViewById(R.id.s_email);
            semail = s_email.getText().toString();

            EditText s_phone = (EditText) findViewById(R.id.s_phone);
            sphone = s_phone.getText().toString();

            if(TextUtils.isEmpty(sname)) {
                Toast.makeText(this, "plz enter the name ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(sadd)) {
                Toast.makeText(this, "plz enter the address ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(slic)) {
                Toast.makeText(this, "plz enter the license number  ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(sdistrict)) {
                Toast.makeText(this, "plz enter the district ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(scategory)) {
                Toast.makeText(this, "plz select any of the services ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(sminbudget)) {
                Toast.makeText(this, "plz enter minimum budget ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(smaxbudget)) {
                Toast.makeText(this, "plz enter maximum budget ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(semail)) {
                Toast.makeText(this, "plz enter the email id  ", Toast.LENGTH_SHORT).show();
                return;
            }

            if(s_phone.getText().toString().length() < 10) {
                Toast.makeText(this, "plz enter the contact number ", Toast.LENGTH_SHORT).show();
                return;
            }


            ref = database.getReference("service_provider");

            final Map service_provider = new HashMap();
            service_provider.put("key",uid);
            service_provider.put("name", sname);
            service_provider.put("licence_no", slic);
            service_provider.put("address", sadd);
            service_provider.put("district", sdistrict);
            service_provider.put("category", scategory);
            service_provider.put("min_budget", sminbudget);
            service_provider.put("max_budget", smaxbudget);
            service_provider.put("location", slocation);
            service_provider.put("email_id", semail);
            service_provider.put("phone", sphone);

            //uid = ref.push().getKey();
            ref.child(uid).setValue(service_provider);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref1 = storageReference.child("images/"+ uid.toString());
            ref1.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(update.this, "Upload Successful ", Toast.LENGTH_SHORT).show();
                            Uri imageurl = taskSnapshot.getDownloadUrl();
                            String url = imageurl.toString();
                            service_provider.put("image",url);
                            ref.child(uid).setValue(service_provider);
                            Intent intent=new Intent(update.this,server.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(update.this, "Upload Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
