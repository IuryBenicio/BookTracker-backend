package benicio.iury.BookTrackr.Exceptions.bookExceptions;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
