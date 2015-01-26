package ExceptionHandler;
/*
 * Exception class to catch invalid modules 
 */
/**
 *
 * @author Andrew
 */
public class ModuleCodeInvalidException extends Exception {
    String exceptionMessage = "";
    
    public ModuleCodeInvalidException(){
        super();
        exceptionMessage = "unknown error with exception thrown";
    }
    
    public ModuleCodeInvalidException(String error){
        super(error);
        exceptionMessage = error;
    }
    public String getError(){
        return exceptionMessage;
    }
}
