package benicio.iury.BookTrackr.Exceptions;

public class BookNotFounded extends RuntimeException {
    public BookNotFounded(String message) {
        super(message);
    }
}
