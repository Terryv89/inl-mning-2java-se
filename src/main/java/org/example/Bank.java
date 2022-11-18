package org.example;

import java.util.ArrayList;

public class Bank {

    public Bank() {
    }
    public  ArrayList<User> getUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> terrysAccounts = new ArrayList<>();
        terrysAccounts.add(new Accounts("1337", 2000));
        terrysAccounts.add(new Accounts("4903", 3000));
        User terry = new User("Terry", "banker", terrysAccounts);
        userArrayList.add(terry);

        ArrayList<Accounts> adamsAccounts = new ArrayList<>();
        adamsAccounts.add(new Accounts("13378", 2000));
        adamsAccounts.add(new Accounts("13373", 4000));
        User adam = new User("Adam", "adamp", adamsAccounts);
        userArrayList.add(adam);

        ArrayList<Accounts> emeliesAccounts = new ArrayList<>();
        emeliesAccounts.add(new Accounts("13379", 2000));
        emeliesAccounts.add(new Accounts("13279", 6054));
        User emelie = new User("Emelie", "pemelie", emeliesAccounts);
        userArrayList.add(emelie);
        return userArrayList;
    }
    public void setAccountMoney(String account, int balance, String newBalance) {
    }
}