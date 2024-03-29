package com.mitrais.atm.screens;

import com.mitrais.atm.helpers.ValidationHelper;
import com.mitrais.atm.models.AccountModel;
import com.mitrais.atm.models.ValidationModel;
import com.mitrais.atm.screens.enums.ScreenEnum;
import com.mitrais.atm.services.AccountService;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Fund Transfer Screen
 * @author Ardi_PR876
 */
public class FundTransferScreen {
    private static FundTransferScreen INSTANCE;
    
    private FundTransferScreen(){
        
    }
    
    /**
     * Singleton Fund Transfer Screen
     * @return FundTransferScreen INSTANCE
     */
    public static FundTransferScreen getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new FundTransferScreen();
        }
        return INSTANCE;
    }
    
    /**
        * Fund Transfer Screen
        * @param account Account
        * @param database List of Account
        * @return goToScreen String
    */
    public String fundTransfer(AccountModel account, List<AccountModel> database) {
        boolean repeat = false;
        
        String goToScreen = ScreenEnum.TRANSACTION.name();
        
        boolean validDestination;

        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Fund Transfer");
            System.out.println("Please enter destination account and press enter to continue ");
            System.out.println("or press enter to go back to Transaction Screen: ");
            System.out.print("Destination: ");

            Scanner scannerDestination = new Scanner(System. in);
            
            String destination = scannerDestination.nextLine().trim();
            
            validDestination = ValidationHelper.onlyNumberValidation(destination);

            if (validDestination) {
                Predicate<AccountModel> predicate = p -> p.getAccountNumber().equals(destination);
                
                Optional<AccountModel> destinationAccount = database.stream().filter(predicate).
                        findFirst();

                try {
                    AccountModel destinationAcc = destinationAccount.get();
                    
                    ValidationModel amountValidation = amountScreen(account);
                    
                    if (amountValidation.isValid()) {
                        Double amount = Double.parseDouble(amountValidation.getMessage());
                        
                        ValidationModel confirmationValidation = confirmationScreen(account, 
                                destinationAcc, amount);
                        
                        boolean valid = confirmationValidation.isValid();
                    
                        goToScreen = confirmationValidation.getMessage();

                        if (valid && goToScreen.equals(ScreenEnum.FUNDTRANSFER.name())) {
                            repeat = true;
                        } else if (valid) {
                            repeat = false;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Invalid account");
                    repeat = false;
                }
            } else if (!validDestination && !destination.isEmpty()) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Invalid account");
                repeat = false;
            }
        } while (repeat);
        
        return goToScreen;
    }
    
    /**
        * Transfer Amount Screen
        * @param account
        * @return 
    */
    private ValidationModel amountScreen(AccountModel account) {
        ValidationModel validationModel = new ValidationModel();
        
        System.out.println("---------------------------------------------------------");
        System.out.println("Please enter transfer amount and press enter to continue");
        System.out.println("or press enter to go back to Transaction: ");
        System.out.print("Amount: ");
        
        Scanner scannerAmount = new Scanner(System. in);

        String strAmount = scannerAmount.nextLine().trim();

        boolean validAmount = ValidationHelper.onlyNumberValidation(strAmount);
        
        Double balance = account.getBalance();

        if (validAmount) {
            Double amount = Double.parseDouble(strAmount);
            
            if (amount > 1000) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Maximum amount to withdraw is $1000");
            } else if (amount < 1) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Minimum amount to withdraw is $1");
            } else if (amount > balance) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Insufficient balance $" + amount);
            } else {
                validationModel.setValid(true);
                validationModel.setMessage(strAmount);
            }
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println("Invalid Amount: should only contains numbers");
        }
        
        return validationModel;
    }
    
    /**
        * Transfer Confirmation Screen
        * @param account
        * @param destinationAcc
        * @param amount
        * @return ValidationHelper
    */
    private ValidationModel confirmationScreen(AccountModel account, AccountModel destinationAcc, 
            Double amount) {
        
        AccountService accountService = AccountService.getInstance();
        
        ValidationModel validationModel = new ValidationModel();
        
        String destinationNumber = destinationAcc.getAccountNumber();
        
        String destinationName = destinationAcc.getName();
        
        String refNumber = getRandomString();
        
        System.out.println("---------------------------------------------------------");
        System.out.print("Reference Number: " + refNumber);
        System.out.println(" (This is an autogenerated number)");
        System.out.print("press enter to continue ");
        
        String scannerRefNumber = new Scanner(System. in).nextLine().trim();

        System.out.println("---------------------------------------------------------");
        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account \t : " + destinationNumber);
        System.out.println("Destination Name \t : " + destinationName);
        System.out.println("Transfer Amount \t : $" + amount);
        System.out.println("Reference Number \t : " + refNumber);
        System.out.println("");
        System.out.println("1. Confirm Transaction");
        System.out.println("2. Cancel Transaction");
        System.out.print("Choose Option[2]: ");
        
        String scannerConfirmTransaction = new Scanner(System. in).nextLine().trim();

        if (scannerConfirmTransaction.equals("1")) {
            boolean transferSucceed = accountService.fundTransfer(account, destinationAcc, 
                    amount);

            if (transferSucceed) {
                String goToScreen = summary(account, destinationAcc, amount, refNumber);
                validationModel.setValid(true);
                validationModel.setMessage(goToScreen);
            }
        } else if (scannerConfirmTransaction.equals("2") || scannerConfirmTransaction.isEmpty()) {
            String goToScreen = ScreenEnum.FUNDTRANSFER.name();
            validationModel.setValid(true);
            validationModel.setMessage(goToScreen);
        }
        
        return validationModel;
    }
    
    /**
        * Summary Screen
        * @param account Account
        * @param destination Account
        * @param amount Double
        * @param refNumber String
        * @return ScreenEnum String
    */
    private String summary(AccountModel account, AccountModel destination, Double amount, 
            String refNumber) {
        
        String destinationNumber = destination.getAccountNumber();
        
        String destinationName = destination.getName();
        
        Double balance = account.getBalance();

        System.out.println("---------------------------------------------------------");
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account \t : " + destinationNumber);
        System.out.println("Destination Name \t : " + destinationName);
        System.out.println("Transfer Amount \t : $" + amount);
        System.out.println("Reference Number \t : " + refNumber);
        System.out.println("Balance \t\t : $" + balance);
        System.out.println("");
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose Option[2]: ");
        
        String scannerConfirmTransaction = new Scanner(System. in).nextLine().trim();
        
        if (scannerConfirmTransaction.equals("1")) {
            return ScreenEnum.TRANSACTION.name();
        } else {
            return ScreenEnum.LOGIN.name();
        }
    }
    
    /**
        * Get 6 Digits Random String
        * @return Random String
    */
    private String getRandomString() {
        SecureRandom random = new SecureRandom();
        
        String DATA_FOR_RANDOM_STRING = "0123456789";
        
        StringBuilder sb = new StringBuilder(6);
        
        for (int i = 0; i < 6; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        
        return sb.toString();
    }
}
