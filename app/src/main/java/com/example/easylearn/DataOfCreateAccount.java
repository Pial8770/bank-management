package com.example.easylearn;

public class DataOfCreateAccount {

    String customersName;
    Integer customerAccountNumber;
    String customersId;
    Integer customerBalance;

    public DataOfCreateAccount(){


    }

    public DataOfCreateAccount(String customersName, Integer customerAccountNumber, String customersId, Integer customerBalance) {
        this.customersName = customersName;
        this.customerAccountNumber = customerAccountNumber;
        this.customersId = customersId;
        this.customerBalance = customerBalance;
    }

    public String getCustomersName() {
        return customersName;
    }

    public Integer getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public String getCustomersId() {
        return customersId;
    }

    public Integer getCustomerBalance() {
        return customerBalance;
    }
}
