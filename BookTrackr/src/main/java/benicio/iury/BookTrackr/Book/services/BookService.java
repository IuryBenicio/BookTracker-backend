package benicio.iury.BookTrackr.Book.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import benicio.iury.BookTrackr.Book.mappings.BookMapper;
import benicio.iury.BookTrackr.Book.DTO.BookDTO;
import benicio.iury.BookTrackr.DTOS.ResAndReq.RecomendedBooksDTO;
import benicio.iury.BookTrackr.Exceptions.bookExceptions.BookAlreadyExistsException;
import benicio.iury.BookTrackr.Exceptions.bookExceptions.BookMaxPages;
import benicio.iury.BookTrackr.Exceptions.bookExceptions.BookNotFounded;
import benicio.iury.BookTrackr.Utils.PromptsUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import benicio.iury.BookTrackr.Book.entities.BookEntity;
import benicio.iury.BookTrackr.Book.repositories.BookRepository;

@Service
public class BookService {
    private BookMapper bookMapper;
    private BookRepository bookRepository;
    private ChatClient chatClient;
    private PromptsUtil prompts;

    public BookService(BookMapper bookMapper, BookRepository bookRepository, ChatClient.Builder chatClientBuilder, PromptsUtil prompts) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.chatClient = chatClientBuilder.build();
        this.prompts = prompts;
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
        boolean bookExist = bookRepository.existsByTitle(bookDTO.title());
        if(bookExist){
            throw new BookAlreadyExistsException("Já existe um livro com esse título");
        }
        BookEntity bookEntity = bookMapper.toEntity(bookDTO);
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

        bookEntity.setId(bookEdit.id());
        bookEntity.setTitle(bookEdit.title());
        bookEntity.setAutor(bookEdit.autor());
        bookEntity.setData(bookEdit.data());
        bookEntity.setPages(bookEdit.pages());
        bookEntity.setUser(bookEdit.user());
        bookEntity.setCapaUrl(bookEdit.capaUrl());
        bookEntity.setReaded(bookEdit.readed());

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

    public String recomendedIaBooks(List<RecomendedBooksDTO> ultimosLivros) throws IOException {
        StringBuilder prompt = new StringBuilder(prompts.loadPrompt("prompt-recomendation"));
        ultimosLivros.forEach(book -> prompt.append(" - ").append(book).append("\n"));

        try{
            String responseIa = chatClient.prompt().user(prompt.toString()).call().content();

            return responseIa;
        }catch (Exception e){
            throw new IOException("Tivemos problemas com a resposta da IA");
        }
    }
}


