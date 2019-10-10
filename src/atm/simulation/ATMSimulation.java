package atm.simulation;

import java.util.List;

/**
 *
 * @author Ardi_PR876
 */
public class ATMSimulation {
    public static void main(String[] args) {
        List<Account> database = AccountService.getAccountList();
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Welcome To ATM Simulation");
            System.out.println("---------------------------------------------------------");
            Account account = LoginService.Login(database);
            if(account != null) {
                Transaction.TransactionScreen(account, database);
            }
        } while(true);
        
    }
    
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
    
}
