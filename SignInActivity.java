package com.example.user.onlinefoodbloggers;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity {
    private Button entersignInButton;
    private Button enterbackButton;

    FirebaseAuth mAuth;
    EditText EmailUser;
    EditText PassText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        entersignInButton = (Button)findViewById(R.id.enterSignInId);
        enterbackButton = (Button)findViewById(R.id.enterBackId);
        mAuth = FirebaseAuth.getInstance();
        EmailUser = findViewById(R.id.EmailUser);
        PassText = findViewById(R.id.PassUser);


        entersignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnterSignInActivity();
            }
        });

        enterbackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openEnterBackActivity();
            }
        });
    }
    public void openEnterBackActivity(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openEnterSignInActivity(){



        signinUser(EmailUser.getText().toString() , PassText.getText().toString());

    }

    public  void signinUser(String email , String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MY_PROJECT", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent =new Intent(SignInActivity.this, EnterSignInActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MY_PROJECT", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

}
