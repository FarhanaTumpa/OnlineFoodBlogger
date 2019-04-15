package com.example.user.onlinefoodbloggers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class EnterSignInActivity extends AppCompatActivity {


    Button Items;
    Button Signout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_sign_in);

        AllItemsShowData.mySelectedItems.clear();
        AllItemsShowData.lstInfoAll.removeAll(AllItemsShowData.lstInfoAll);
        EnterBackActivity.AllItemsList.removeAll(EnterBackActivity.AllItemsList);


        Items  = findViewById(R.id.ItemasPage);
        Signout = findViewById(R.id.Signout);

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent it = new Intent(EnterSignInActivity.this,MainActivity.class);
                startActivity(it);
                finish();

            }
        });


        Items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EnterSignInActivity.this,AllItemsShowData.class);
                startActivity(it);

                Toast.makeText(EnterSignInActivity.this, "Items " , Toast.LENGTH_LONG).show();
            }
        });
    }
}
