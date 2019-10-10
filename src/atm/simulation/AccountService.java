package atm.simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ardi_PR876
 */
public class AccountService {
    public static List<Account> getAccountList() {
        List<Account> list = new ArrayList<>();
        
        list.add(new Account("John Doe", "112233", "012108", 100));
        list.add(new Account("Jane Doe", "112244", "932012", 30));
        
        return list;
    }
    
    public static boolean DeductBalance(Account account, double amount) {
        if(account.balance < amount){
            System.out.println("---------------------------------------------------------");
            System.out.println("Insufficient balance $" +amount);
            return false;
        } else {
            account.balance -= amount;
            return true;
        }
    }
    
    public static boolean FundTransfer(Account account, Account destination, Double amount) {
        if(account.balance < amount){
            System.out.println("---------------------------------------------------------");
            System.out.println("Insufficient balance $" +amount);
            return false;
        } else {
            account.balance -= amount;
            destination.balance += amount;
            return true;
        }
    }
}
