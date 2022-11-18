package org.example;

public class Accounts {

    private String accountNumber;
    private  int balance;

    public Accounts(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public int getBalance() {
        return balance;
    }
    public String setBalance(int balance) {
        this.balance = balance;
        return null;
    }
}