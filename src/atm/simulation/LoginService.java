package atm.simulation;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author Ardi_PR876
 */
public class LoginService {
    public static Account Login(List<Account> database) {
        Account account = null;
        boolean validAccountNumber = false;
        String accountNumber;
        String pin;
        do {
            System.out.print("Enter Account Number: ");
            Scanner scannerAccountNumber = new Scanner(System. in);
            accountNumber = scannerAccountNumber.nextLine();
            
            Validation validation = Validation.LoginValidation(accountNumber, "Account Number");
            validAccountNumber = validation.valid;
            if(!validAccountNumber) System.out.println(validation.message);
        } while(!validAccountNumber);
        
        boolean validPin = false;
        do {
            System.out.print("Enter PIN: ");
            Scanner scannerPin = new Scanner(System. in);
            pin = scannerPin.nextLine();
            
            Validation validation = Validation.LoginValidation(pin, "PIN");
            validPin = validation.valid;
            if(!validPin) System.out.println(validation.message);
        } while(!validPin);
        
        String finalAccountNumber = accountNumber;
        String finalPin = pin;
        
        Predicate<Account> findAccount = p -> p.getAccountNumber().equals(finalAccountNumber) && p.getPin().equals(finalPin);
        Optional<Account> matchingAccount = database.stream().filter(findAccount).findFirst();
        
        if(matchingAccount.isPresent())
        {
            account = matchingAccount.get();
        } else {
            System.out.println("Invalid Account Number/PIN");
        }
        
        return account;
    }
    
    
}
