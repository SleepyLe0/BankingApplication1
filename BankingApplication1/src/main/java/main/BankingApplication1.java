package main;

import system.Account;
import system.Bank;

import java.util.Random;
import java.util.Scanner;

public class BankingApplication1 {

    public static void main(String[] args) {
        int choice = 0;
        int number;
        String name;
        double balance;
        double amount;
        Bank bank = new Bank();
        Account account;
        do {
            System.out.println("Main Menu");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (choice) {
                case 1:
                    // Display All Accounts
                    System.out.println("Display All Accounts");
                    bank.listAccounts();
                    break;
                case 2:
                    // Open New Account
                    System.out.println("Open New Account");
                    System.out.print("Enter Account Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = sc.nextDouble();
                    number = generateAccountNumber();
                    account = new Account(number, name, balance);
                    bank.openAccount(account);
                    break;
                case 3:
                    // Close Existing Account
                    System.out.println("Close Existing Account");
                    System.out.print("Enter Account Number: ");
                    number = sc.nextInt();
                    bank.closeAccount(number);
                    break;
                case 4:
                    // Deposit
                    System.out.println("Deposit");
                    System.out.print("Enter Account Number: ");
                    number = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    amount = sc.nextDouble();
                    bank.depositMoney(number,amount);
                    break;
                case 5:
                    // Withdraw
                    System.out.println("Withdraw");
                    System.out.print("Enter Account Number: ");
                    number = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    amount = sc.nextDouble();
                    bank.withdrawMoney(number,amount);
                    break;
                case 6:
                    // Exit
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();
        } while (choice != 6);
    }

    public static int generateAccountNumber() {
        Random rand = new Random();
        int number = 100000 + rand.nextInt(900000);
        return number;
    }
}
