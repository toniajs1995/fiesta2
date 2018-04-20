package com.example.project.fiesta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_SIGN_IN = 123;
    private Spinner spinner;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner=(Spinner) findViewById(R.id.spinner);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener((View.OnClickListener) this);

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());


        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //intent to new activity
               // Intent intent = new Intent(LoginActivity.this,choose.class);
               // startActivity(intent);
            }
            else {

                Toast.makeText(LoginActivity.this, "Sign in Failed !!!", Toast.LENGTH_SHORT).show();
                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    @Override
    public void onClick(View view){
        String option;
        option=spinner.getSelectedItem().toString();
        //Toast.makeText(choose.this,option.toString(), Toast.LENGTH_SHORT).show();
        if(option.equals("Client")){
            Intent intent = new Intent(LoginActivity.this,client.class);
            startActivity(intent);
        }
        if(option.equals("Service Provider")){
            Intent intent = new Intent(LoginActivity.this,server.class);
            startActivity(intent);
        }
    }
}
