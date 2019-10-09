/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardi_PR876
 */
public class Account {
    public String name;
    public String pin;
    public double balance;
    public String accountNumber;
    
    public Account(String name, String accountNumber, String pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }
    
    public static List<Account> getAccountList() {
        List<Account> list = new ArrayList<>();
        
        list.add(new Account("John Doe", "012108", "112233", 100));
        list.add(new Account("Jane Doe", "932012", "112244", 30));
        
        return list;
    }

    public String getPin() {
        return pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }  
    
}
