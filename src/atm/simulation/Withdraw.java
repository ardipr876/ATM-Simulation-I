/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm.simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Ardi_PR876
 */
public class Withdraw {
    public static String WithdrawScreen(Account account) {
        System.out.println("---------------");
        System.out.println("Withdraw Screen");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose option[5]: ");

        Scanner scannerOption = new Scanner(System. in);
        String option = scannerOption.nextLine().trim();
        
        // Choice on summary screen, go to Transaction page or Exit (Login page)
        String choice = ""; 

        switch (option) {
            case "1":
                choice = DeductBalance(account, 10);
                break;
            case "2":
                choice = DeductBalance(account, 50);
                break;
            case "3":
                choice = DeductBalance(account, 100);
                break; 
            case "4":
                choice = OtherWithdrawScreen(account);
                break;
        }
        
        return choice;
    }
    
    public static String OtherWithdrawScreen(Account account) {
        System.out.println("-----------------------------");
        System.out.println("Other Withdraw (Multiple $10)");
        System.out.print("Enter amount to withdraw: ");
        
        Scanner scannerAmount = new Scanner(System. in);
        String value = scannerAmount.nextLine().trim();
        String choice = "Transaction";
        
        Validation validation = Validation.WithdrawValidation(value);
        if(validation.valid){
            double amount = Double.parseDouble(value);
            choice = DeductBalance(account, amount);
        } else {
            System.out.println(validation.message);
        }
        
        return choice;
    }
    
    public static String DeductBalance(Account account, double amount) {
        if(account.balance < amount){
            System.out.println("-----------------------------");
            System.out.println("Insufficient balance $" +amount);
            return "Transaction";
        } else {
            account.balance -= amount;
            return Summary(account.balance, amount);
        }
    }
    
    public static String Summary(double balance, double amount){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        String formatDateTime = date.format(formatter);
        
        System.out.println("-------");
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
        
        String choice;
        if(option.equals("1")) {
            choice = "Transaction";
        } else {
            choice = "Login";
        }
        
        return choice;
    }
}
