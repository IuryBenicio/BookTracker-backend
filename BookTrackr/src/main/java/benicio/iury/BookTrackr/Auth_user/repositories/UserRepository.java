package benicio.iury.BookTrackr.Auth_user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import benicio.iury.BookTrackr.Auth_user.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {

    UserEntity findByEmail(String email);
    UserEntity findByName(String name);

}
