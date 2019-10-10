package atm.simulation;

/**
 *
 * @author Ardi_PR876
 */
public class Account {
    public String name;
    public String pin;
    public double balance;
    public String accountNumber;
    
    public Account(String name, String accountNumber, String pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }  
    
}
