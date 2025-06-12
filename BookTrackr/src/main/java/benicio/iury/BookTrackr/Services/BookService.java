package benicio.iury.BookTrackr.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import benicio.iury.BookTrackr.DTOS.BookDTO;
import benicio.iury.BookTrackr.Entities.BookEntity;
import benicio.iury.BookTrackr.Mappings.BookMapper;
import benicio.iury.BookTrackr.Respositories.BookRepository;

@Service
public class BookService {
    private BookMapper bookMapper;
    private BookRepository bookRepository;
    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }
    
    //Services
    public List<BookDTO> getBookList(long id){
        Optional <List<BookEntity>> booksOptional = bookRepository.findAllByUser_Id(id);

        if(booksOptional.isEmpty()){
            return null;
        }

        List<BookEntity> booksExist = booksOptional.get();

        List<BookDTO> books = booksExist.stream()
                            .map(bookMapper::toDTO)
                            .collect(Collectors.toList());
        return books;
    }
}


