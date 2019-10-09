package atm.simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Ardi_PR876
 */
public class AccountService {
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
