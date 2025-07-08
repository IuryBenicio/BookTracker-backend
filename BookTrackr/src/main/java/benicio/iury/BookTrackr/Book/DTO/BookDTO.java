package benicio.iury.BookTrackr.Book.DTO;

import benicio.iury.BookTrackr.Auth_user.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record BookDTO(long id ,@NotBlank String title,
                      String autor,
                      @NotBlank int pages,
                      Date data,
                      int readGoal,
                      String capaUrl,
                      UserEntity user,
                      int readed) {
}
