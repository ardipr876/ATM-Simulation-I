package com.mitrais.atm;

import java.util.ArrayList;
import java.util.List;

/**
 * get account, deduct balance, transfer fund
 * @author Ardi_PR876
 */
public class AccountService {
    
    /**
     * get account list
     * @return List Account 
     */
    public static List<Account> getAccountList() {
        List<Account> list = new ArrayList<>();
        
        list.add(new Account("John Doe", "112233", "012108", 100));
        list.add(new Account("Jane Doe", "112244", "932012", 30));
        
        return list;
    }
    
    /**
    * Deduct Balance Method, used for deducting balance when withdrawing fund
     * @param account
     * @param amount
     * @return boolean
    */
    public static boolean deductBalance(Account account, double amount) {
        Double balance = account.getBalance();
        
        if (balance < amount) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Insufficient balance $" +amount);
            return false;
        } else {
            balance -= amount;
            
            account.setBalance(balance);
            
            return true;
        }
    }
    
    /**
    * Fund Transfer Method, used on transfer fund transaction
     * @param account
     * @param destination
     * @param amount
     * @return boolean
    */
    public static boolean fundTransfer(Account account, Account destination, Double amount) {
        Double balance = account.getBalance();
        
        Double destinationBalance = destination.getBalance();
        
        if (balance < amount) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Insufficient balance $" +amount);
            return false;
        } else {
            balance -= amount;
            
            account.setBalance(balance);
            
            destinationBalance += amount;
            
            destination.setBalance(destinationBalance);
            
            return true;
        }
    }
}
