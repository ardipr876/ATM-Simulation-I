package atm.simulation;

/**
 *
 * @author Ardi_PR876
 */
public class Validation {
    public boolean valid;
    public String message;
    
    public Validation() {
        this.valid = false;
        this.message = "";
    }
    
    public static Validation LoginValidation(String value, String type) {
        Validation result = new Validation();
        
        if(value.length() == 6) {
            String regex = "[0-9]+";
            if(!value.matches(regex)) {
                result.valid = false;
                result.message = type + " should only contains numbers";
            } else {
                result.valid = true;
            }
        } else {
            result.valid = false;
            result.message = type + " should have 6 digits length";
        } 
        
        return result;
    }
    
    public static Validation WithdrawValidation(String value) {
        Validation result = new Validation();
        String regex = "[0-9]+";
        double amount = Double.parseDouble(value);
        if(amount > 1000) {
            result.valid = false;
            result.message = "Maximum amount to withdraw is $1000";
        } else if(!value.matches(regex)) {
            result.valid = false;
            result.message = "Invalid ammount";
        } else if(amount % 10 != 0) {
            result.valid = false;
            result.message = "Invalid ammount";
        } else {
            result.valid = true;
        }
        
        return result;
    }
}
