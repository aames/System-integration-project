/*
 * Exception class to catch module not found
 * 
 */
package ExceptionHandler;

/**
 *
 * @author Andrew
 */

public class ModuleNotFoundException extends Exception {
    String exceptionMessage = "";
    
    public ModuleNotFoundException(){
        super();
        exceptionMessage = "unknown error with exception thrown";
    }
    
    public ModuleNotFoundException(String error){
        super(error);
        exceptionMessage = error;
    }
    public String getError(){
        return exceptionMessage;
    }
}
    
