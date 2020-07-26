package com.example.easylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateAccount extends AppCompatActivity {

    public EditText createAccountEditTextId1;
    public EditText createAccountEditTextId2;
    public EditText createAccountEditTextId3;
    public Button createAccountButton;




    DatabaseReference databaseCustomerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        databaseCustomerInfo = FirebaseDatabase.getInstance().getReference("customerInfo");


        createAccountEditTextId1 = (EditText) findViewById(R.id.createAccountName);
        createAccountEditTextId2 = (EditText) findViewById(R.id.createAccountAccountId);
        createAccountEditTextId3 = (EditText) findViewById(R.id.createAccountBalance);
        createAccountButton = (Button) findViewById(R.id.createAccountButtonId);



        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

    }


    private void createAccount(){

            final  String name = createAccountEditTextId1.getText().toString().trim();
         final String accountNumber = createAccountEditTextId2.getText().toString().trim();
         final String initialBalance= createAccountEditTextId3.getText().toString().trim();

         final int balance = Integer.parseInt(initialBalance);

         final int accountNumberInt = Integer.parseInt(accountNumber);

       // accountNumberInt = accountNumberInt+1;

        //Toast.makeText(getApplicationContext(), "AC"+accountNumberInt, Toast.LENGTH_SHORT).show();


                        if(!((TextUtils.isEmpty(name) && (TextUtils.isEmpty(accountNumber) && (TextUtils.isEmpty(initialBalance)))))){




                            databaseCustomerInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                                        if(dataSnapshot1.getValue(DataOfCreateAccount.class).getCustomerAccountNumber().equals(accountNumberInt)){


                                            Toast.makeText(getApplicationContext(), "Already Exists", Toast.LENGTH_SHORT).show();

                                           System.exit(1);


                                        }
                                    }
                                    String id = databaseCustomerInfo.push().getKey();

                                    DataOfCreateAccount dataOfCreateAccount = new DataOfCreateAccount(name,accountNumberInt,id,balance);
                                    databaseCustomerInfo.child(id).setValue(dataOfCreateAccount);

                                    Toast.makeText(getApplicationContext(),"Customer added",Toast.LENGTH_SHORT).show();





                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });







                        }else{

                            Toast.makeText(getApplicationContext(), "Enter a name", Toast.LENGTH_SHORT).show();
                        }

        createAccountEditTextId1.setText("");
        createAccountEditTextId2.setText("");
        createAccountEditTextId3.setText("");

    }















}
