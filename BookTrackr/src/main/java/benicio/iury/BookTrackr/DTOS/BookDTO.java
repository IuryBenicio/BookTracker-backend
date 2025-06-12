package benicio.iury.BookTrackr.DTOS;

import java.math.BigDecimal;
import java.util.Date;

import benicio.iury.BookTrackr.Entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private long id;
    private String title;
    private String autor;
    private int pages;
    private Date data;
    private String capaUrl;
    private UserEntity user;
    private BigDecimal readed;
}
