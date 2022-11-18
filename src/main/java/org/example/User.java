package org.example;

import java.util.ArrayList;

public class User {
    String name;
    String password;
    ArrayList<Accounts> accounts;

    public User(String name, String password, ArrayList<Accounts> accounts){
        this.name = name;
        this.password = password;
        this.accounts = accounts;
    }
    public boolean login(String name, String password){
        return this.name.equals(name) && this.password.equals(password);
    }
    public ArrayList<Accounts> getAccounts() {
        return accounts;
    }
    public String getName() {
        return name;
    }
}