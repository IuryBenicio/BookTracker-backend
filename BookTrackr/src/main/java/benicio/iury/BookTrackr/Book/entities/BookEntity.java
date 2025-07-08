package benicio.iury.BookTrackr.Book.entities;

import java.util.Date;

import benicio.iury.BookTrackr.Auth_user.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "tb_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private long id;

    @Column(name = "title")
    @NotBlank(message = "Título do livro é obrigatório")
    private String title;

    @Column(name = "autor")
    private String autor;

    @Column(name = "pages")
    @NotBlank(message = "Quantidade de páginas do livro é obrigatório")
    private int pages;

    @Column(name = "data")
    private Date data;

    @Column(name = "read_goal")
    private int readGoal;

    @Column(name = "capaUrl")
    private String capaUrl;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Column(name = "readed")
    private int readed = 0;

}
