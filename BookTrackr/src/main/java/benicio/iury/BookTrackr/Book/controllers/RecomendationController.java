package benicio.iury.BookTrackr.Book.controllers;

import benicio.iury.BookTrackr.DTOS.ResAndReq.RecomendedBooksDTO;
import benicio.iury.BookTrackr.Book.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("recomendation")
public class RecomendationController {
    BookService bookService;

    public RecomendationController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book-sugester")
    public ResponseEntity<String> getRecommendations(@RequestBody List<RecomendedBooksDTO> ultimosLivros) throws IOException {
        String IaResponse = bookService.recomendedIaBooks(ultimosLivros);
        return ResponseEntity.ok(IaResponse);
    }
}
