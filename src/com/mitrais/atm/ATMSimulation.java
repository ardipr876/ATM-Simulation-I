package com.mitrais.atm;

import java.util.List;

/**
 * ATM Simulation main class
 * @author Ardi_PR876
 */
public class ATMSimulation {
    
    /**
     * ATM Simulation main method
     * @param args 
     */
    public static void main(String[] args) {
        List<Account> database = AccountService.getAccountList();
        
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Welcome To ATM Simulation");
            System.out.println("---------------------------------------------------------");
            Account account = LoginService.login(database);
            
            if(account != null) {
                Transaction.transactionScreen(account, database);
            }
            
        } while (true);
        
    }
}
