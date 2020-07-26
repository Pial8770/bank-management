package com.example.easylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetails extends AppCompatActivity {


    DatabaseReference databaseReference;

    private ListView customerDetailsLV;

    private List<DataOfCreateAccount> customerList;
    private CustomAdapter customAdapter;

    public static final String CUSTOMER_NAME = "customerName";
    public static final String CUSTOMER_ID = "customerId";
    public static final String CUSTOMER_INITIAL_BALANCE ="customerBalance";
    public static final String CUSTOMER_ACCOUNT_NUMBER = "customerAccountNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);


        databaseReference = FirebaseDatabase.getInstance().getReference("customerInfo");

        customerList = new ArrayList<>();

        customAdapter = new CustomAdapter(CustomerDetails.this,customerList);


        customerDetailsLV =(ListView) findViewById(R.id.customerDetailsLV);



        customerDetailsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                DataOfCreateAccount customer = customerList.get(position);

                String balance = String.valueOf(customer.getCustomerBalance());
                String accountNumber = String.valueOf(customer.getCustomerAccountNumber());

                Intent intent = new Intent(getApplicationContext(),deposit.class);

                intent.putExtra(CUSTOMER_NAME,customer.getCustomersName());
                intent.putExtra(CUSTOMER_ID,customer.getCustomersId());
                intent.putExtra(CUSTOMER_ACCOUNT_NUMBER,accountNumber);
                intent.putExtra(CUSTOMER_INITIAL_BALANCE,balance);


                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                customerList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){


                    DataOfCreateAccount customer = dataSnapshot1.getValue(DataOfCreateAccount.class);

                    customerList.add(customer);
                }
                customerDetailsLV.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
