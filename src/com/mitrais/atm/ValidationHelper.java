package com.mitrais.atm;

/**
 * For validation process
 * @author Ardi_PR876
 */
public class ValidationHelper {
    private boolean valid;
    
    private String message;
    
    public ValidationHelper() {
        this.valid = false;
        this.message = "";
    }
    
    /**
        * Login Validation
        * @param value String
        * @param type String
        * @return Validation
    */
    public static ValidationHelper loginValidation(String value, String type) {
        ValidationHelper result = new ValidationHelper();
        
        if (value.length() == 6) {
            if (!onlyNumberValidation(value)) {
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
    
    /**
        * Withdraw Validation
        * @param value String
        * @return Validation
    */
    public static ValidationHelper withdrawValidation(String value) {
        ValidationHelper result = new ValidationHelper();
        
        if (!onlyNumberValidation(value)) {
            result.valid = false;
            result.message = "Ammount should only contains numbers";
            return result;
        }
        
        double amount = Double.parseDouble(value);
        
        if (amount > 1000) {
            result.valid = false;
            result.message = "Maximum amount to withdraw is $1000";
        } else if(amount % 10 != 0) {
            result.valid = false;
            result.message = "Invalid ammount";
        } else {
            result.valid = true;
        }
        
        return result;
    }
    
    /**
        * Only Number Validation
        * @param value
        * @return boolean
    */
    public static boolean onlyNumberValidation(String value) {
        String regex = "[0-9]+";
        
        return value.matches(regex);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
