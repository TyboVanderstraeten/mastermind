package exceptions;

public class NormaalSpelToegangException extends IllegalArgumentException{
    
    public NormaalSpelToegangException(){
        super("Deze mogelijkheidsgraad is pas toegankelijk als u 20 makkelijke spellen heeft gewonnen.");
    }
    
    public NormaalSpelToegangException(String message){
        super(message);
    }

}
