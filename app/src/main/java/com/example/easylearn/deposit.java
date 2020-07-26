package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deposit extends AppCompatActivity {

    private Button depositButton;
    private Button depositButton2;
    private TextView depositTV;
    private TextView depositTV2;
    private TextView depositTV3;
    private TextView depositTV4;
    private EditText depositET;
    private EditText depositET2;
    String intentName;
    String intentBal;
    int intentAccountNumber;
    int afterDepositBal;
    String intentId;
    int addedAmount;
    int addedAmount2;
    int initialBalance;
    int sum;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        depositButton = (Button) findViewById(R.id.depositButton);
        depositButton2 = (Button) findViewById(R.id.depositButton2);
        depositTV = (TextView) findViewById(R.id.depositTextV);
        depositTV2 = (TextView) findViewById(R.id.depositTextV2);
        depositTV3 = (TextView) findViewById(R.id.depositTextV3);
        depositTV4 = (TextView) findViewById(R.id.depositTextV4);
        depositET = (EditText) findViewById(R.id.depositEditText);
        depositET2 = (EditText) findViewById(R.id.depositEditText2);







        Intent intent = getIntent();

        intentName = intent.getStringExtra(CustomerDetails.CUSTOMER_NAME);

        depositTV.setText("Name  : "+intentName);


        intentBal = intent.getStringExtra(CustomerDetails.CUSTOMER_INITIAL_BALANCE);
        depositTV2.setText("Balance  : "+intentBal);

        String accountNumber = intent.getStringExtra(CustomerDetails.CUSTOMER_ACCOUNT_NUMBER);
        depositTV3.setText("Account Number  : "+accountNumber);


        intentId = intent.getStringExtra(CustomerDetails.CUSTOMER_ID);

        //Toast.makeText(this, intentId, Toast.LENGTH_SHORT).show();


        intentAccountNumber = Integer.parseInt(accountNumber);



        //balance addition

        initialBalance = Integer.parseInt(intentBal);


        //try {
           // addedAmount = Integer.parseInt(depositET.getText().toString());

       // }catch(NumberFormatException e){
           // Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        //}







        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = intentName;
                int accountNumber = intentAccountNumber;
                String customerId = intentId;
                addedAmount = Integer.parseInt(depositET.getText().toString());
                sum = initialBalance+addedAmount;

                int customerBal = sum;



                updateCustomer(name,accountNumber,customerId,customerBal);

            }
        });

        depositButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = intentName;
                int accountNumber = intentAccountNumber;
                String customerId = intentId;
                addedAmount2 = Integer.parseInt(depositET2.getText().toString());
                sum = initialBalance-addedAmount2;

                int customerBal = sum;



                updateCustomer(name,accountNumber,customerId,customerBal);


            }
        });








    }



    private boolean updateCustomer(String name, int accountNumber, String id, int balance){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("customerInfo").child(id);
        DataOfCreateAccount customer = new DataOfCreateAccount(name,accountNumber,id,balance);

        databaseReference.setValue(customer);

        Toast.makeText(getApplicationContext(), "Balance updated!", Toast.LENGTH_SHORT).show();

        return true;
    }

}
