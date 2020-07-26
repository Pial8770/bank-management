package com.example.easylearn;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataOfCreateAccount> {


    private Activity context;


    private List<DataOfCreateAccount> customerList;

    public CustomAdapter(Activity context ,List<DataOfCreateAccount> customerList) {
        super(context, R.layout.customer_details_sample_layout, customerList);

        this.context = context;

        this.customerList = customerList;
    }


    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.customer_details_sample_layout,null,true);
        DataOfCreateAccount Customer = customerList.get(position);

        TextView t1 = view.findViewById(R.id.sampleLayoutTV1);
        TextView t2 = view.findViewById(R.id.sampleLayoutTV2);
        TextView t3 = view.findViewById(R.id.sampleLayoutTV3);

        t1.setText("Name  :"+Customer.getCustomersName());
        t2.setText("Account Number  :"+Customer.getCustomerAccountNumber());
        t3.setText("Balance  :"+Customer.getCustomerBalance());


        return view;
    }


}
