package benicio.iury.BookTrackr.DTOS.ResAndReq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private String name;
    private String email;
}
