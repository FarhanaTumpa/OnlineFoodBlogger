package com.example.user.onlinefoodbloggers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllItemsShowData extends AppCompatActivity {


    RecyclerView MyRecyclerView;
    RecyclerView.Adapter myAdapter;
    List<String> lst = new ArrayList<>();
    public static  List<FoodItemInfo> lstInfoAll = new ArrayList<>();
    public static Map<Integer,FoodItemInfo> mySelectedItems = new HashMap<>();



    public static int SelectedItemscount = 0;

    public static double TotalAmount = 0.0;
    Button AddToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items_show_data);

        AddToCartButton = findViewById(R.id.AddToCart);
        AddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(AllItemsShowData.TotalAmount > 0){
                    Intent it = new Intent(AllItemsShowData.this,EnterBackActivity.class);
                    startActivity(it);

                }
                else {
                    Toast.makeText(AllItemsShowData.this,"Your total Amount is 0.0 TK ",Toast.LENGTH_LONG).show();
                }
            }
        });


        FirebaseDatabase myFirebaseDatabase_UserInfo;
        DatabaseReference myDatabaseReference_UserInfo_reference;


        myFirebaseDatabase_UserInfo = FirebaseDatabase.getInstance();
        myDatabaseReference_UserInfo_reference = myFirebaseDatabase_UserInfo.getReference().child("FOOD_ITEMS");

        myDatabaseReference_UserInfo_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
//{ key = FOOD_ITEMS, value = {Pizza={Price=540, Name=Pizza}, Test={Price=100, Name=Test}} }
                Log.d("My_Project", dataSnapshot.toString());

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Log.d("My_Project", dsp.getKey());
                    Log.d("My_Project", dsp.toString() );

                    for (DataSnapshot dt : dsp.getChildren()){
                        Log.d("My_Project", dt.getKey());
                        Log.d("My_Project", dt.toString());

                        FoodItemInfo fd = dt.getValue(FoodItemInfo.class);
                        fd.toString();
                        lstInfoAll.add(fd);

                        /*
                        String NameItem = "";
                        String PriceItem = "";
                        for (DataSnapshot dn : dt.getChildren()) {
                            Log.d("My_Project", dn.getKey()+"<>"+dn.toString());
                     q       Log.d("My_Project", dn.child("Name").toString());
                            //FoodItemInfo fd = new FoodItemInfo(dn.toString() ,dn.toString());
                            //FoodItemInfo fd = dt.getValue(FoodItemInfo.class);
                            if(dn.getKey().equals("Name")){
                                NameItem = dn.toString();
                            }
                            else if(dn.getKey().equals("Price")){
                                PriceItem = dn.toString();
                            }

                        }
                        Log.d("My_Project",NameItem + " " + PriceItem);
                        //FoodItemInfo fd = new FoodItemInfo(NameItem, PriceItem);
                        //lstInfoAll.add(fd);
                        NameItem = "";
                        PriceItem = "";

                        //FoodItemInfo fd = new FoodItemInfo(dt.child(dt.getKey()).child("Name").toString() , dt.child(dt.getKey()).child("Price").toString());

                        //fd.toString();
                        //lstInfoAll.add(fd);
                        */
                    }

                }

                /*
                lstInfoAll.add(new FoodItemInfo("Pizza", "540"));

                lstInfoAll.add(new FoodItemInfo("Burger", "150"));
                lstInfoAll.add(new FoodItemInfo("Sandwitch", "120"));
                lstInfoAll.add(new FoodItemInfo("Soup", "240"));
                lstInfoAll.add(new FoodItemInfo("Cold Coffee", "50"));
                lstInfoAll.add(new FoodItemInfo("Salad", "160"));
                lstInfoAll.add(new FoodItemInfo("Pasta", "360"));
                lstInfoAll.add(new FoodItemInfo("Soft Drinks", "30"));
                lstInfoAll.add(new FoodItemInfo("Pizza Roman", "540"));
                lstInfoAll.add(new FoodItemInfo("Burger BBQ", "150"));
                lstInfoAll.add(new FoodItemInfo("Chicken Sandwitch ", "120"));
                lstInfoAll.add(new FoodItemInfo("Thai Soup", "240"));
                lstInfoAll.add(new FoodItemInfo("Hot Coffee", "50"));
                lstInfoAll.add(new FoodItemInfo("Cashewnut Salad", "160"));
                lstInfoAll.add(new FoodItemInfo("Pasta Basta", "360"));
                lstInfoAll.add(new FoodItemInfo("Cold Drinks", "30"));
*/



                MyRecyclerView = (RecyclerView) findViewById(R.id.ItemsShowRecycleView);
                MyRecyclerView.setHasFixedSize(true);
                MyRecyclerView.setLayoutManager(new LinearLayoutManager(AllItemsShowData.this));

                myAdapter = new IndividualListAdapter(lstInfoAll, AllItemsShowData.this);
                MyRecyclerView.setAdapter(myAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










        //lst.add("Pizza");
        //lst.add("Pasta");
        //lst.add("Burger");
        //lst.add("Sandwitch");
        //lst.add("Spagette");
        //lst.add("Chicken Fry");
        //lst.add("Corn Soup");
        //lst.add("Thai soup");
        //lst.add("Coffee");
        //lst.add("Salad");




    }

    @Override
    public void onBackPressed() {
        AllItemsShowData.mySelectedItems.clear();
        AllItemsShowData.lstInfoAll.removeAll(AllItemsShowData.lstInfoAll);
        EnterBackActivity.AllItemsList.removeAll(EnterBackActivity.AllItemsList);
        EnterBackActivity.lstInfoAllQuantity.clear();
        super.onBackPressed();
    }
}
