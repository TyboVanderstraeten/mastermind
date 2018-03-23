package exceptions;

public class WachtwoordException extends IllegalArgumentException{

    public WachtwoordException() {
        super("Wachtwoord moet beginnen en eindigen met een cijfer, met daartussen minstens 6 letters (groot of klein)!");
    }

    public WachtwoordException(String message) {
        super(message);
    }
    
    

}
