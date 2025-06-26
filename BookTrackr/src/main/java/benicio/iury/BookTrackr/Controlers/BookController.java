package benicio.iury.BookTrackr.Controlers;

import java.awt.print.Book;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import benicio.iury.BookTrackr.DTOS.BookDTO;
import benicio.iury.BookTrackr.Services.BookService;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    //Controllers
    @GetMapping("/get-books/{id}")
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

    @PostMapping("/add-book")
    public ResponseEntity<?> createBook(@Valid BookDTO bookDTO){
        BookDTO bookSaved = bookService.createBook( bookDTO);
        return ResponseEntity.ok(bookSaved);
    }

    @GetMapping("/get-book")
    public ResponseEntity<BookDTO> getBook(@PathVariable long id){
        BookDTO bookReturned = bookService.getBook(id);
        return ResponseEntity.ok(bookReturned);
    }

    @PatchMapping("/edit-book/{id}")
    public ResponseEntity<BookDTO> editBook(@PathVariable long id, @RequestBody BookDTO bookEdit){
        BookDTO bookEdited = bookService.editBook(id, bookEdit);
        return ResponseEntity.ok(bookEdited);
    }

    @PatchMapping("/read-book/{id}")
    public ResponseEntity<BookDTO> readBook(@PathVariable long id, @RequestBody int pages){
        BookDTO bookEdited = bookService.readBook(id, pages);
        return ResponseEntity.ok(bookEdited);
    }
}
