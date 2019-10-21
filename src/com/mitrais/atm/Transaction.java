package com.mitrais.atm;

import java.util.List;
import java.util.Scanner;

/**
 * For transaction process
 * @author Ardi_PR876
 */
public class Transaction {
    
    /**
        * Transaction Screen
        * @param account Account
        * @param database List of Account
    */
    public static void transactionScreen(Account account, List<Account> database) {
        boolean repeat = true;
        
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Transaction Screen");
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Exit");
            System.out.print("Please choose option[3]: ");
            
            Scanner scannerOption = new Scanner(System. in);
            
            String option = scannerOption.nextLine().trim();
            
            String goToScreen;
            
            if ("1".equals(option)) {
                goToScreen = Withdraw.withdrawScreen(account);
                if(ScreenEnum.Screen.LOGIN.name().equals(goToScreen)) repeat = false;
            } else if("2".equals(option)) {
                goToScreen = FundTransfer.fundTransferScreen(account, database);
                if(ScreenEnum.Screen.LOGIN.name().equals(goToScreen)) repeat = false;
            } else if("4".equals(option)) {
                Double balance = account.getBalance();
                System.out.println("---------------------------------------------------------");
                System.out.println("Your Balance : $" + balance);
            } else if("3".equals(option) || option == null || option.isEmpty()) {
                repeat = false;
            }
            
        } while (repeat);
    }
}