package com.example.project.fiesta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class choose extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinner;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        spinner=(Spinner) findViewById(R.id.spinner);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view){
        String option;
        option=spinner.getSelectedItem().toString();
        //Toast.makeText(choose.this,option.toString(), Toast.LENGTH_SHORT).show();
        if(option.equals("Client")){
            Intent intent = new Intent(choose.this,client.class);
            startActivity(intent);
        }
        if(option.equals("Service Provider")){
            Intent intent = new Intent(choose.this,server.class);
            startActivity(intent);
        }
    }
}
