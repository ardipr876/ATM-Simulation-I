package atm.simulation;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ardi_PR876
 */
public class Transaction {
    public static void TransactionScreen(Account account, List<Account> database) {
        boolean repeat = true;
        do {
            System.out.println("-----------------------------");
            System.out.println("Transaction Screen");
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Exit");
            System.out.print("Please choose option[3]: ");
            
            Scanner scannerOption = new Scanner(System. in);
            String option = scannerOption.nextLine().trim();
            
            if ("1".equals(option)){
                String withdrawChoice = Withdraw.WithdrawScreen(account);
                if("Login".equals(withdrawChoice)) repeat = false;
            } else if("2".equals(option)){
                FundTransfer.FundTransferScreen(account, database);
            } else if("3".equals(option) || option == null || option.isEmpty()) {
                repeat = false;
            }
        } while(repeat);
    }
}