package benicio.iury.BookTrackr.Respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import benicio.iury.BookTrackr.DTOS.BookDTO;
import benicio.iury.BookTrackr.Entities.BookEntity;

@Repository
public interface BookRepository extends JpaRepository <BookEntity, Long>{
    Optional<List<BookEntity>> findAllByUser_Id(long userId);
}
