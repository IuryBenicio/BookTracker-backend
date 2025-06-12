package benicio.iury.BookTrackr.Controlers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import benicio.iury.BookTrackr.DTOS.BookDTO;
import benicio.iury.BookTrackr.Services.BookService;

@RestController
@RequestMapping("book")
public class BookController {
    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    //Controllers
    @GetMapping("/getbooks/{id}")
    public ResponseEntity <?> getBooks(@PathVariable long id){
        try{
            List<BookDTO> books = bookService.getBookList(id);
            if(books == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não encontramos livros");
            }
            return ResponseEntity.ok(books);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não conseguimos recuperar seus livros" + e.getMessage());
        }
    }
}
