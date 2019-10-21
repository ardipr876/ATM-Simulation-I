package com.mitrais.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * For withdrawal process
 * @author Ardi_PR876
 */
public class Withdraw {
    
    /**
        * Withdraw Screen
        * @param account
        * @return goToScreen String
    */
    public static String withdrawScreen(Account account) {
        // Choice on summary screen, go to Transaction page or Exit (Login page)
        String goToScreen = "";
        
        boolean succeed;
        
        Double balance = account.getBalance();
        
        System.out.println("---------------------------------------------------------");
        System.out.println("Withdraw Screen");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose option[5]: ");

        Scanner scannerOption = new Scanner(System. in);
        
        String option = scannerOption.nextLine().trim();
        
        switch (option) {
            case "1":
                succeed = AccountService.deductBalance(account, 10);
                
                if (succeed) {
                    balance -= 10;
                    goToScreen = summary(balance, 10);
                }
                break;
            case "2":
                succeed = AccountService.deductBalance(account, 50);
                
                if (succeed) {
                    balance -= 50;
                    goToScreen = summary(balance, 50);
                }
                break;
            case "3":
                succeed = AccountService.deductBalance(account, 100);
                
                if (succeed) {
                    balance -= 100;
                    goToScreen = summary(balance, 100);
                }
                break; 
            case "4":
                goToScreen = otherWithdrawScreen(account);
                break;
        }
        
        return goToScreen;
    }
    
    /**
        * Other Withdraw Screen
        * @param account
        * @return goToScreen String
    */
    public static String otherWithdrawScreen(Account account) {
        String goToScreen = ScreenEnum.Screen.TRANSACTION.name();
        
        boolean succeed;
        
        Double balance = account.getBalance();
        
        ValidationHelper validation;
        
        System.out.println("---------------------------------------------------------");
        System.out.println("Other Withdraw (Multiple $10)");
        System.out.print("Enter amount to withdraw: ");
        
        Scanner scannerAmount = new Scanner(System. in);
        
        String value = scannerAmount.nextLine().trim();
        
        validation = ValidationHelper.withdrawValidation(value);
        
        if (validation.isValid()) {
            double amount = Double.parseDouble(value);
            
            succeed = AccountService.deductBalance(account, amount);
            
            if (succeed) {
                goToScreen = summary(balance, amount);
            }
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println(validation.getMessage());
        }
        
        return goToScreen;
    }
    
    /**
        * Summary Screen
        * @param balance
        * @param amount
        * @return ScreenEnum.name() String
    */
    public static String summary(double balance, double amount){
        LocalDateTime date = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        
        String formatDateTime = date.format(formatter);
        
        System.out.println("---------------------------------------------------------");
        System.out.println("Summary");
        System.out.println("Date : " + formatDateTime);
        System.out.println("Withdraw : $" + amount);
        System.out.println("Balance : $" + balance);
        
        System.out.println("");
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose Option[2]: ");
        
        Scanner scannerOption = new Scanner(System. in);
        
        String option = scannerOption.nextLine().trim();
        
        if (option.equals("1")) {
            return ScreenEnum.Screen.TRANSACTION.name();
        } else {
            return ScreenEnum.Screen.LOGIN.name();
        }
    }
}
