package com.example.user.onlinefoodbloggers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class IndividualListAdapter extends RecyclerView.Adapter<IndividualListAdapter.MyViewHolder> {


    List<String> AllString;
    List<FoodItemInfo> AllStringFood;
    Context context;

/*
    public IndividualListAdapter(List<String> allString , Context myContext) {
        AllString = allString;
        context = myContext;
    }

*/

    public IndividualListAdapter(List<FoodItemInfo> allString , Context myContext) {
        AllStringFood = allString;
        context = myContext;
    }
    public IndividualListAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_items,viewGroup,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
       // myViewHolder.textView.setText(AllString.get(i));


        FoodItemInfo in = AllStringFood.get(i);
        //Log.i("My_Project",in.toString());
        myViewHolder.textView.setText(in.getName());
        myViewHolder.textViewPrice.setText(in.getPrice());

        if(in.getImageLink().equals("") && in.getImageLink().isEmpty()){
            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(myViewHolder.ImageViewFood);
        }
        else{
            Picasso.get().load(in.getImageLink()).into(myViewHolder.ImageViewFood);
        }


        myViewHolder.myCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    FoodItemInfo check = AllStringFood.get(i);
                    AllItemsShowData.mySelectedItems.put(i,check);
                    //AllItemsShowData.AllItemsList.add(AllItemsShowData.SelectedItemscount , i );
                    //AllItemsShowData.SelectedItemscount++;



                    String price = check.getPrice().replace(" TK","");
                    AllItemsShowData.TotalAmount += Double.parseDouble(price) ;
                    Toast.makeText(myViewHolder.MyView.getContext(),AllItemsShowData.TotalAmount+"",Toast.LENGTH_LONG).show();
                }
                else{
                    FoodItemInfo check = AllStringFood.get(i);
                    AllItemsShowData.mySelectedItems.remove(i);
                    //AllItemsShowData.SelectedItemscount--;
                    //AllItemsShowData.AllItemsList.remove(i);



                    String price = check.getPrice().replace(" TK","");
                    AllItemsShowData.TotalAmount -= Double.parseDouble(price) ;
                    if(AllItemsShowData.TotalAmount <= 0.0){
                        AllItemsShowData.TotalAmount = 0.0;
                    }
                    Toast.makeText(myViewHolder.MyView.getContext(),AllItemsShowData.TotalAmount+"",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //return AllString.size();
        return AllStringFood.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView textViewPrice;
        public CheckBox myCheckBox;
        public ImageView ImageViewFood;
        public View MyView;

        public MyViewHolder(View v) {
            super(v);
            MyView = v;
            textView = v.findViewById(R.id.SingleItemsTitle);
            textViewPrice = v.findViewById(R.id.SingleItemsTitlePrice);
            myCheckBox = v.findViewById(R.id.SelectedFoodCheckbox);
            ImageViewFood = v.findViewById(R.id.imageViewFoodItem);
        }
    }
}
