package benicio.iury.BookTrackr.Book.mappings;

import benicio.iury.BookTrackr.Book.entities.BookEntity;
import benicio.iury.BookTrackr.Book.DTO.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(BookEntity entity);
    BookEntity toEntity(BookDTO dto);
}
