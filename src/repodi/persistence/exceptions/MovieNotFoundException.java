package repodi.persistence.exceptions;


public class MovieNotFoundException extends Exception{

    public MovieNotFoundException(){}

    public MovieNotFoundException(String message){
        super(message);
    }

    public MovieNotFoundException(String message, Throwable exception){
        super(message, exception);
    }
}
