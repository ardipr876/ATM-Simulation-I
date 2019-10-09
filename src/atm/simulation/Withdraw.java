package atm.simulation;

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
                choice = AccountService.DeductBalance(account, 10);
                break;
            case "2":
                choice = AccountService.DeductBalance(account, 50);
                break;
            case "3":
                choice = AccountService.DeductBalance(account, 100);
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
            choice = AccountService.DeductBalance(account, amount);
        } else {
            System.out.println(validation.message);
        }
        
        return choice;
    }
}
