package benicio.iury.BookTrackr.Book.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import benicio.iury.BookTrackr.Book.entities.BookEntity;

@Repository
public interface BookRepository extends JpaRepository <BookEntity, Long>{
    Optional<List<BookEntity>> findAllByUser_Id(long userId);
    boolean existsByTitle(String title);
}
