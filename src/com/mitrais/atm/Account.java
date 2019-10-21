package com.mitrais.atm;

/**
 * Account property
 * @author Ardi_PR876
 */
public class Account {
    private String name;
    
    private String pin;
    
    private double balance;
    
    private String accountNumber;
    
    /**
     * account constructor
     * @param name
     * @param accountNumber
     * @param pin
     * @param balance 
     */
    public Account(String name, String accountNumber, String pin, double balance) {
        this.name = name;
        
        this.pin = pin;
        
        this.balance = balance;
        
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
