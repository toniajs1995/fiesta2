package com.example.project.fiesta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class birthday extends AppCompatActivity {

    Button search_budget,search_location;
    ImageButton imagedeco,imagecat,imagemake,imagetrans,imageout,imagemis,imagemedia,imagecake,imageenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        try {

            search_location = (Button) findViewById(R.id.search_location);
            search_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, location.class);
                    startActivity(i);
                }
            });

            search_budget= (Button) findViewById(R.id.search_budget);
            search_budget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, budget.class);
                    startActivity(i);
                }
            });

            imagedeco = (ImageButton) findViewById(R.id.imagedeco);
            imagedeco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, deco.class);
                    startActivity(i);
                }
            });

            imagecat = (ImageButton) findViewById(R.id.imagecat);
            imagecat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, cat.class);
                    startActivity(i);
                }
            });

           imagecake = (ImageButton) findViewById(R.id.imagecake);
            imagecake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, cake.class);
                    startActivity(i);
                }
            });

            imagetrans = (ImageButton) findViewById(R.id.imagetrans);
            imagetrans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, trans.class);
                    startActivity(i);
                }
            });

            imagemis = (ImageButton) findViewById(R.id.imagemis);
            imagemis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, mis.class);
                    startActivity(i);
                }
            });

            imagemedia = (ImageButton) findViewById(R.id.imagemedia);
            imagemedia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, med.class);
                    startActivity(i);
                }
            });

            imageenter = (ImageButton) findViewById(R.id.imageenter);
            imageenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(birthday.this, enter.class);
                    startActivity(i);
                }
            });
        }
        catch(Exception e){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(birthday.this,bookmark.class);
            //intent.putExtra("key",key);
            /// Toast.makeText(client.this, "key", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
