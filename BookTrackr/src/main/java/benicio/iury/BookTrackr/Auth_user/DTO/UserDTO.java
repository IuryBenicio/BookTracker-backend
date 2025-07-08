package benicio.iury.BookTrackr.Auth_user.DTO;

import benicio.iury.BookTrackr.Book.entities.BookEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDTO(String name,
                      @NotBlank String email,
                      @NotBlank String password,
                      List<BookEntity> books,
                      String imageUrl) {
}
