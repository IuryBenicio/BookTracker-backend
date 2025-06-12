package benicio.iury.BookTrackr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO <T> {
    private T data;
    private String message;
    private boolean success;
}
