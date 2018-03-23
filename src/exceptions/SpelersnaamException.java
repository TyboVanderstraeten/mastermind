package exceptions;

public class SpelersnaamException extends IllegalArgumentException{

    public SpelersnaamException() {
        super("Spelersnaam mag max. 40 karakters bevatten en mag niet leeg zijn!")
    }

    public SpelersnaamException(String message) {
        super(message);
    }
    
    

}
