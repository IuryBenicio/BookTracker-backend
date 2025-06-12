package benicio.iury.BookTrackr.Mappings;

import org.springframework.stereotype.Component;

import benicio.iury.BookTrackr.DTOS.BookDTO;
import benicio.iury.BookTrackr.Entities.BookEntity;

@Component
public class BookMapper {
    
    public BookEntity toModel(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();

        bookEntity.setId(bookDTO.getId());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setUser(bookDTO.getUser());
        bookEntity.setPages(bookDTO.getPages());
        bookEntity.setAutor(bookDTO.getAutor());
        bookEntity.setData(bookDTO.getData());
        bookEntity.setCapaUrl(bookDTO.getCapaUrl());
        bookEntity.setReaded(bookDTO.getReaded());

        return bookEntity;
    }

    public BookDTO toDTO(BookEntity bookEntity){
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(bookEntity.getId());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setUser(bookEntity.getUser());
        bookDTO.setPages(bookEntity.getPages());
        bookDTO.setAutor(bookEntity.getAutor());
        bookDTO.setData(bookEntity.getData());
        bookDTO.setCapaUrl(bookEntity.getCapaUrl());
        bookDTO.setReaded(bookEntity.getReaded());

        return bookDTO;
    }
}
