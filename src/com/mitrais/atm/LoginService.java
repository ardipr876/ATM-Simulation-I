package com.mitrais.atm;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * For login process
 * @author Ardi_PR876
 */
public class LoginService {
    
    /**
        * Login Process
        * @param database List of Account
        * @return account Account
    */
    public static Account login(List<Account> database) {
        Account account = null;
        
        boolean validAccountNumber;
        
        String accountNumber;
        
        String pin;
        
        boolean validPin;
        
        ValidationHelper validation;
        
        do {
            System.out.print("Enter Account Number: ");
            Scanner scannerAccountNumber = new Scanner(System. in);
            
            accountNumber = scannerAccountNumber.nextLine();
            
            validation = ValidationHelper.loginValidation(accountNumber, "Account Number");
            
            validAccountNumber = validation.isValid();
            
            if (!validAccountNumber) {
                System.out.println(validation.getMessage());
            }
        } while (!validAccountNumber);
        
        do {
            System.out.print("Enter PIN: ");
            Scanner scannerPin = new Scanner(System. in);
            
            pin = scannerPin.nextLine();
            
            validation = ValidationHelper.loginValidation(pin, "PIN");
            
            validPin = validation.isValid();
            
            if (!validPin) {
                System.out.println(validation.getMessage());
            }
        } while (!validPin);
        
        String finalAccountNumber = accountNumber;
        
        String finalPin = pin;
        
        Predicate<Account> findAccount = p -> 
                p.getAccountNumber().equals(finalAccountNumber) && p.getPin().equals(finalPin);
        
        Optional<Account> matchingAccount = database.stream().filter(findAccount).findFirst();
        
        if (matchingAccount.isPresent()) {
            account = matchingAccount.get();
        } else {
            System.out.println("Invalid Account Number/PIN");
        }
        
        return account;
    }
}
