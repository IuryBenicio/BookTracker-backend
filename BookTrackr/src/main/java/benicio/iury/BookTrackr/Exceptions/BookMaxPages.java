package benicio.iury.BookTrackr.Exceptions;

public class BookMaxPages extends RuntimeException {

    public BookMaxPages(String message){
        super(message);
    }

}
