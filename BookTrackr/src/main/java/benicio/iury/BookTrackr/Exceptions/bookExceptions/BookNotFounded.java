package benicio.iury.BookTrackr.Exceptions.bookExceptions;

public class BookNotFounded extends RuntimeException {
    public BookNotFounded(String message) {
        super(message);
    }
}
