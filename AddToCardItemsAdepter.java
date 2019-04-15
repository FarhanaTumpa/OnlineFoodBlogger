package com.example.user.onlinefoodbloggers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddToCardItemsAdepter extends BaseAdapter {

    Context myContext;
    LayoutInflater myInflater;
    Map<Integer,FoodItemInfo> Food;
    List< Integer > List;



    public AddToCardItemsAdepter(Context myContext, Map<Integer, FoodItemInfo> food,List< Integer > List) {
        this.myContext = myContext;
        Food = food;
        this.List  = List;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        if (convertView == null){
            myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            convertView = myInflater.inflate(R.layout.add_to_card_list_items,parent,false);

        }

        TextView t1 = (TextView) convertView.findViewById(R.id.textView);
        final TextView t2 = (TextView) convertView.findViewById(R.id.textView2);
        TextView t3 = (TextView) convertView.findViewById(R.id.textView3);
        TextView t4 = (TextView) convertView.findViewById(R.id.textView4);
        final TextView t5 = (TextView) convertView.findViewById(R.id.textView5);


        t4.setText(Food.get(List.get(i)).getName());

        t5.setText(Food.get(List.get(i)).getPrice());

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double d = Double.parseDouble(t2.getText().toString());
                d++;
                int val = (int)d;

                String s1 = t5.getText().toString();
                String replaceString = s1.replace(" TK","");
                double d2 = Double.parseDouble(Food.get(List.get(i)).getPrice().replace(" TK","")) * d ;
                int val2 = (int)d2;



                EnterBackActivity.TotalPrice += (int)Double.parseDouble(Food.get(List.get(i)).getPrice().replace(" TK",""));
                EnterBackActivity.TotalVat += (int) (Double.parseDouble(Food.get(List.get(i)).getPrice().replace(" TK","")) * 0.15 );
                EnterBackActivity.Subtotal.setText(EnterBackActivity.TotalPrice+" TK");
                EnterBackActivity.vat.setText(EnterBackActivity.TotalVat+" TK");
                EnterBackActivity.TotalAmount = EnterBackActivity.TotalVat + EnterBackActivity.TotalPrice;
                EnterBackActivity.TotalPriceTextView.setText(EnterBackActivity.TotalAmount+" TK");


                t2.setText( val+ "");
                t5.setText(val2 + " TK");
                EnterBackActivity.lstInfoAllQuantity.put(List.get(i)+"",val+"");

            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double d = Double.parseDouble(t2.getText().toString());
                if(d >= 2){
                    d--;
                    EnterBackActivity.TotalPrice -= (int)Double.parseDouble(Food.get(List.get(i)).getPrice().replace(" TK",""));
                    EnterBackActivity.TotalVat -= (int) (Double.parseDouble(Food.get(List.get(i)).getPrice().replace(" TK","")) * 0.15 );
                    EnterBackActivity.Subtotal.setText(EnterBackActivity.TotalPrice+" TK");
                    EnterBackActivity.vat.setText(EnterBackActivity.TotalVat+" TK");
                    EnterBackActivity.TotalAmount = EnterBackActivity.TotalVat + EnterBackActivity.TotalPrice;
                    EnterBackActivity.TotalPriceTextView.setText(EnterBackActivity.TotalAmount+" TK");

                }

                int val = (int)d;
                String s1 = t5.getText().toString();
                String replaceString = s1.replace(" TK","");
                double d2 = Double.parseDouble(Food.get(List.get(i)).getPrice().replace(" TK","")) * d ;
                int val2 = (int)d2;

                t2.setText(val + "");
                t5.setText(val2 + " TK");

                EnterBackActivity.lstInfoAllQuantity.put(List.get(i)+"",val+"");

            }
        });


        return convertView;
    }
}
