package benicio.iury.BookTrackr.Exceptions;

import benicio.iury.BookTrackr.Exceptions.bookExceptions.BookAlreadyExistsException;
import benicio.iury.BookTrackr.Exceptions.bookExceptions.BookMaxPages;
import benicio.iury.BookTrackr.Exceptions.bookExceptions.BookNotFounded;
import benicio.iury.BookTrackr.Exceptions.bookExceptions.IaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<String> handleBookNotFounded(BookNotFounded ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    public ResponseEntity<String> handleBookAlreadyExists(BookAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    public ResponseEntity<String> handleBookMaxPages(BookMaxPages ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    public ResponseEntity<String> handleIaResponse(IaResponse ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
