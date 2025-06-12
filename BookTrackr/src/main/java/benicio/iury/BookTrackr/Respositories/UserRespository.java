package benicio.iury.BookTrackr.Respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import benicio.iury.BookTrackr.Entities.UserEntity;

@Repository
public interface UserRespository extends JpaRepository<UserEntity , Long> {

    UserEntity findUserByEmail(String email);
}
