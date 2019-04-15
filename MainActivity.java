package com.example.user.onlinefoodbloggers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button signInButton;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("USER");

        myRef.setValue("Hello, World! Test");

        signInButton = (Button) findViewById(R.id.btnSignIn);
        signUpButton = (Button) findViewById(R.id.btnSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignInActivity();
            }
        });
    }
    public void openSignUpActivity(){
        Intent intent =new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void openSignInActivity(){
        Intent intent =new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent =new Intent(this, EnterSignInActivity.class);
            startActivity(intent);
        }
        super.onStart();
    }
}
