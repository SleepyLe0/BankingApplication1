package system;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private String name;

    public Bank(){
    }

    public Bank(String name) {
        this.name = name;
    }

    public void openAccount(Account account) {
        Connection connection = BankingConnection.connect();
        String sql = "INSERT INTO account (accNumber,accName,accBalance) VALUES(?,?,?)";
        PreparedStatement prepareStatement;
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1,account.getNumber());
            prepareStatement.setString(2,account.getName());
            prepareStatement.setDouble(3,account.getBalance());
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeAccount(int number) {
        Connection connection = BankingConnection.connect();
        String sql = "DELETE FROM account WHERE accNumber = ?";
        PreparedStatement prepareStatement;
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1,number);
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveAccount(Account account) {
        System.out.println("Account saved successfully");
    }

    public void listAccounts() {
        Connection connection = BankingConnection.connect();
        String sql = "SELECT * FROM account";
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                System.out.print(results.getString(1) + " "
                        + results.getString(2) + " "
                        + results.getString(3));
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void depositMoney(int number, double amount) {
        Account account = getAccount(number);
        account.deposit(amount);
        System.out.println("Balance : " + account.getBalance());
        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET accBalance = ? WHERE accNumber = ?";
        PreparedStatement prepareStatement;
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setDouble(1,account.getBalance());
            prepareStatement.setInt(2,number);
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void withdrawMoney(int number, double amount) {
        Account account = getAccount(number);
        account.withdraw(amount);
        System.out.println("Balance : " + account.getBalance());
        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET accBalance = ? WHERE accNumber = ?";
        PreparedStatement prepareStatement;
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setDouble(1,account.getBalance());
            prepareStatement.setInt(2,number);
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccount(int number) {
        Connection connection = BankingConnection.connect();
        String sql = "SELECT * FROM account WHERE accNumber = ?";
        PreparedStatement prepareStatement;
        Account account = null;
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1,number);
            ResultSet results = prepareStatement.executeQuery();
            results.next();
            String accName = results.getString(2);
            double accBalance = results.getDouble(3);
            account = new Account(number,accName,accBalance);
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    public double getBalance(int number) {
        return 0;
    }
}


