package benicio.iury.BookTrackr.DTOS;

import java.util.List;
import benicio.iury.BookTrackr.Entities.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private List<BookEntity> books;
    private String imageUrl;
}
