package com.mitrais.atm.services;

import com.mitrais.atm.models.AccountModel;
import com.mitrais.atm.services.implement.IAccountService;
import java.util.ArrayList;
import java.util.List;

/**
 * Account Service
 * @author Ardi_PR876
 */
public class AccountService implements IAccountService {
    private static AccountService INSTANCE;
    
    private AccountService(){
        
    }
    
    /**
     * Singleton Account Service
     * @return AccountService INSTANCE
     */
    public static AccountService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new AccountService();
        }
        return INSTANCE;
    }
    
    /**
     * get account list
     * @return List Account 
     */
    @Override
    public List<AccountModel> getAccountList() {
        List<AccountModel> list = new ArrayList<>();
        
        list.add(new AccountModel("John Doe", "112233", "012108", 100));
        list.add(new AccountModel("Jane Doe", "112244", "932012", 30));
        
        return list;
    }
    
    /**
    * Deduct Balance Method, used for deducting balance when withdrawing fund
     * @param account
     * @param amount
     * @return boolean
    */
    @Override
    public boolean deductBalance(AccountModel account, double amount) {
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
    @Override
    public boolean fundTransfer(AccountModel account, AccountModel destination, Double amount) {
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
