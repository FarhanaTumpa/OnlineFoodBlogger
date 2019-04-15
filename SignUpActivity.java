package com.example.user.onlinefoodbloggers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button SignUp;
    Button BackButton;

    EditText Name;
    EditText email;
    EditText pass;
    EditText phone;

    public  static  String UsName;
    public  static  String UsEmail;
    public  static  String UsPass;
    public  static  String UsPhone;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        SignUp  =  findViewById(R.id.SignUpId);
        BackButton = findViewById(R.id.backId);
        Name = findViewById(R.id.nameId);
        email = findViewById(R.id.emailId);
        pass = findViewById(R.id.newPassId);
        phone = findViewById(R.id.phnNumId);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = pass.getText().toString();

                SignUpActivity.UsEmail = email.getText().toString();
                SignUpActivity.UsPass = password;
                SignUpActivity.UsName = Name.getText().toString();
                SignUpActivity.UsPhone= phone.getText().toString();



                if(password.length() >= 6 ){
                    SignUpFireBase(email.getText()+"" , pass.getText()+"");
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Please Give password minimum 6 digits  . ",Toast.LENGTH_SHORT).show();
                }



            }
        });






    }



    public  void  SignUpFireBase(String email , String pass){


        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("My_PROJECT", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    UserInfo us = new UserInfo(SignUpActivity.UsName,SignUpActivity.UsEmail,SignUpActivity.UsPass,SignUpActivity.UsPhone);

                    FirebaseDatabase myFirebaseDatabase_UserInfo = FirebaseDatabase.getInstance();
                    DatabaseReference myDatabaseReference_UserInfo_reference = myFirebaseDatabase_UserInfo.getReference().child("USERS").child(FirebaseAuth.getInstance().getUid());
                    myDatabaseReference_UserInfo_reference.setValue(us);


                    Toast.makeText(SignUpActivity.this,"User is Created ", Toast.LENGTH_SHORT).show();
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("My_PROJECT", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            Intent intent =new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        super.onStart();
    }
}
