package org.example;

import java.util.Scanner;

public class Bankomat {

    public Bank bank;

    public Bankomat() {
    this.bank = new Bank();
    }

    public Bankomat(Bank bank) {
        this.bank = bank;
    }
    public int checkAccountBalance(String accountNumber, User loggedInUser) throws NullPointerException{
        var loggedInUserAccounts = loggedInUser.getAccounts();
        for (Accounts accounts : loggedInUserAccounts) {
            if (accounts.getAccountNumber().equals(accountNumber)) {
                return accounts.getBalance();
            }
        }
        return -1;
    }

    public int depositMoney(int amountDeposited, User loggedInUser, String accountNumber) {
        var loggedInUserAccounts = loggedInUser.getAccounts();
        for (Accounts accounts : loggedInUserAccounts) {
            if (accounts.getAccountNumber().equals(accountNumber)) {
                if (amountDeposited > 0){
                    String newBalance = accounts.setBalance(amountDeposited + accounts.getBalance());
                    bank.setAccountMoney(accountNumber, amountDeposited, newBalance);
                    return accounts.getBalance();
                }else {
                    throw new RuntimeException("you didnt deposit any money");
                }
            }
        }
        throw new RuntimeException("Wrong account number.");
    }

    public int withdrawMoney(int amountWithdrawn, User loggedInUser, String accountNumber) {
        var loggedInUserAccounts = loggedInUser.getAccounts();
        for (Accounts accounts : loggedInUserAccounts) {
            if (accounts.getAccountNumber().equals(accountNumber)) {
                String newBalance = accounts.setBalance(accounts.getBalance() - amountWithdrawn);
                bank.setAccountMoney(accountNumber, amountWithdrawn, newBalance);
                return accounts.getBalance();
            }
        }
        return -1;
    }

    public void run() {

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to your ATM");
            System.out.println("Press 1 to login");
            System.out.println("Press 2 to exit");
            String optionOne = scanner.nextLine();
            User loggedInUser = null;

            if (optionOne.equals("1")) {
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();
                System.out.println("Enter your password");
                String password = scanner.nextLine();
                loggedInUser = login(name, password);
                if (loggedInUser == null) return;
            } else if (optionOne.equals("2")) {
                System.exit(0);
            } else {
                System.out.println("Not a valid command");
            }
            System.out.println("                     ");
            System.out.println("         ATM         ");
            System.out.println("Choose 1 for Withdraw");
            System.out.println("Choose 2 for Deposit");
            System.out.println("Choose 3 for Check Balance");
            System.out.println("Choose 4 for EXIT");
            System.out.println("Enter your choice: ");
            String optionTwo = scanner.nextLine();

            switch (optionTwo) {
                case "1" -> {
                    System.out.println("What account nr do you wanna withdraw from?");
                    var accountNumber = scanner.nextLine();
                    System.out.println("your current balance is: " + checkAccountBalance(accountNumber, loggedInUser));
                    System.out.println("how much do you wanna withdraw? ");
                    var amountWithdrawn = scanner.nextInt();
                    System.out.println("you withdraw: " + amountWithdrawn);
                    System.out.println("your current balance is: " + withdrawMoney(amountWithdrawn, loggedInUser, accountNumber));
                    System.out.println("Thanks for using our ATM");
                    System.out.println("     ");
                }
                case "2" -> {
                    System.out.println("What account nr do you wanna deposit to?");
                    var accountNumber = scanner.nextLine();
                    System.out.println("your current balance is: " + checkAccountBalance(accountNumber, loggedInUser));
                    System.out.println("how much do you wanna deposit? ");
                    var amountDeposited = scanner.nextInt();
                    try {
                        System.out.println("your current balance is: " + depositMoney(amountDeposited, loggedInUser, accountNumber));
                        System.out.println("you deposited: " + amountDeposited);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Thanks for using our ATM");
                    System.out.println("     ");
                }
                case "3" -> {
                    System.out.println("What account nr do you wanna check?");
                    var accountNumber = scanner.nextLine();
                    System.out.println("your current balance is: " + checkAccountBalance(accountNumber, loggedInUser));
                }
                case "4" -> {
                    System.out.println("Thanks for using the ATM ");
                    System.exit(0);
                    System.out.println(" ");
                }
                default -> System.out.println("wrong input, try again");
            }
        }
    }

    public User login(String name, String password) {
        User loggedInUser = null;
        for (User user : bank.getUsers()) {
            if (user.login(name, password)) {
                loggedInUser = user;
                System.out.println("Welcome back " + loggedInUser.getName());
                break;
            }
        }
        if (loggedInUser == null) {
            System.out.println("Wrong username or password, try again");
            return null;
        }
        return loggedInUser;
    }
}