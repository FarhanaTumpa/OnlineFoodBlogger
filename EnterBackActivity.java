package com.example.user.onlinefoodbloggers;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.DOMImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.user.onlinefoodbloggers.OnlineFoodBloggs.CHANNEL_1_ID;
import static java.lang.Math.ceil;
import static java.lang.Math.negateExact;


public class EnterBackActivity extends AppCompatActivity {

    private Button backButton;

    private Button submitbutton;
    ProgressDialog progress;
    TextView MyTextView;
    private NotificationManagerCompat notificationManager;
    LinearLayout myLinearLayout;
    LinearLayout MyLinearLayoutRoot;

    ListView myListViewFoodItems;
    public static TextView vat ;
    public static TextView Subtotal;
    public static TextView TotalPriceTextView;
    TextView t3 ;
    TextView t4 ;
    TextView t5 ;
    public static int TotalVat;
    public static int TotalPrice;
    public static int TotalAmount;
    public static List< Integer > AllItemsList = new ArrayList<>();
    public static  Map<String , String > lstInfoAllQuantity = new HashMap<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_back);


        FoodItemInfo in ;
        double sum = 0.0;

        Map<Integer,FoodItemInfo> map = AllItemsShowData.mySelectedItems;

        for (Integer key : map.keySet()) {
            AllItemsList.add(key);
            lstInfoAllQuantity.put(key+"","1");
            System.out.println(key + " " + map.get(key));
            in = map.get(key);
            Log.i("MY_SELECTED_ITEMS",key + " " +in.getName()+" "+in.getPrice() );
            sum += Double.parseDouble(in.getPrice().replace(" TK","")) ;

        }



        TotalVat = (int)ceil(sum*0.15);
        TotalPrice = (int)ceil(sum);
        vat = findViewById(R.id.textView7);
        Subtotal = findViewById(R.id.textView9);
        TotalPriceTextView = findViewById(R.id.textView11);
        vat.setText(TotalVat+" TK");
        Subtotal.setText(TotalPrice+" TK");
        TotalAmount = TotalVat + TotalPrice;
        TotalPriceTextView.setText(TotalAmount+" TK");


        myListViewFoodItems = findViewById(R.id.FoodItemsCard);
        AddToCardItemsAdepter at = new AddToCardItemsAdepter(this,AllItemsShowData.mySelectedItems,AllItemsList);
        myListViewFoodItems.setAdapter(at);

        submitbutton = findViewById(R.id.submitbutton);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = new ProgressDialog(EnterBackActivity.this);
                progress.setMessage("Please Wait ...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(false);
                progress.setCancelable(false);
                progress.show();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        FirebaseDatabase myFirebaseDatabase_UserInfo = FirebaseDatabase.getInstance();
                        DatabaseReference myDatabaseReference_UserInfo_reference = myFirebaseDatabase_UserInfo.getReference().child("USERS").child(FirebaseAuth.getInstance().getUid()).child("orders");

                        String keyValue = myDatabaseReference_UserInfo_reference.push().getKey();

                        Map<Integer,FoodItemInfo> SelectmapItems = AllItemsShowData.mySelectedItems;
                        int i = 1;
                        for (Integer key : SelectmapItems.keySet()) {
                            FoodItemInfo it = SelectmapItems.get(key);
                            myDatabaseReference_UserInfo_reference.child(keyValue).child("items").child(i+"").child("FoodName").setValue(it.getName());
                            myDatabaseReference_UserInfo_reference.child(keyValue).child("items").child(i+"").child("FoodPrice").setValue(it.getPrice());
                            myDatabaseReference_UserInfo_reference.child(keyValue).child("items").child(i+"").child("quantity").setValue(EnterBackActivity.lstInfoAllQuantity.get(key+""));
                            Log.i("MY_SELECTED_ITEMS_Fir",key + " " +it.getName()+" "+it.getPrice() );

                            i++;
                        }
                        myDatabaseReference_UserInfo_reference.child(keyValue).child("items").child("vat").setValue(EnterBackActivity.TotalVat+" TK");
                        myDatabaseReference_UserInfo_reference.child(keyValue).child("items").child("TotalAmount").setValue(EnterBackActivity.TotalAmount+" TK");





                            progress.dismiss();
                            Intent myInternt_Error = new Intent(EnterBackActivity.this,EnterSubmit.class);
                        startActivity(myInternt_Error);
                            finish();

                    }
                }, 4000);
            }
        });

    }
    public void openMainActivity(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        EnterBackActivity.AllItemsList.removeAll(EnterBackActivity.AllItemsList);

        super.onBackPressed();
    }
}

