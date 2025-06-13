package benicio.iury.BookTrackr.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import benicio.iury.BookTrackr.Exceptions.BookAlreadyExistsException;
import benicio.iury.BookTrackr.Exceptions.BookMaxPages;
import benicio.iury.BookTrackr.Exceptions.BookNotFounded;
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

    public BookDTO createBook(BookDTO bookDTO){
        boolean bookExist = bookRepository.existsByTitle(bookDTO.getTitle());
        if(bookExist){
            throw new BookAlreadyExistsException("Já existe um livro com esse título");
        }
        BookEntity bookEntity = bookMapper.toModel(bookDTO);
        try{
            BookEntity bookSaved = bookRepository.save(bookEntity);
            return bookMapper.toDTO(bookSaved);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o livro no banco de dados", e);
        }

    }

    public BookDTO getBook(long id){
        Optional<BookEntity> bookReturned = bookRepository.findById(id);
        if (bookReturned.isPresent() == false){
            throw new BookNotFounded("Livro não encontrado");
        }
        return bookMapper.toDTO(bookReturned.get());
    }

    public BookDTO editBook(long id, BookDTO bookEdit){
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFounded("não encontramos seu livro de id: " + id));

        bookEntity.setId(bookEdit.getId());
        bookEntity.setTitle(bookEdit.getTitle());
        bookEntity.setAutor(bookEdit.getAutor());
        bookEntity.setData(bookEdit.getData());
        bookEntity.setPages(bookEdit.getPages());
        bookEntity.setUser(bookEdit.getUser());
        bookEntity.setCapaUrl(bookEdit.getCapaUrl());
        bookEntity.setReaded(bookEdit.getReaded());

        BookEntity bookSaved = bookRepository.save(bookEntity);

        return bookMapper.toDTO(bookSaved);

    }

    public BookDTO readBook(long id, int pages){
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFounded("não encontramos seu livro de id: " + id));

        if((bookEntity.getPages() - bookEntity.getReaded()) < pages){
            throw new BookMaxPages("Número de páginas lidas invalidas");
        }

        bookEntity.setReaded(bookEntity.getReaded() + pages);

        bookRepository.save(bookEntity);

        return bookMapper.toDTO(bookEntity);
    }
}


