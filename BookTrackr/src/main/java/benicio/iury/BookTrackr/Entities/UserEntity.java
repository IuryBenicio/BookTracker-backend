package benicio.iury.BookTrackr.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id")
    private long id;

    @NotBlank(message = "campo obrigatório")
    @Column(name="nome")
    private String name;

    @NotBlank(message = "campo obrigatório")
    @Column(name="email", unique = true)
    private String email;

    @NotBlank(message = "campo obrigatório")
    @Column(name = "password")
    private String password;

    @OneToMany
    @JoinColumn(name = "booksId")
    private List<BookEntity> books;

    @Column(name = "imageUrl")
    private String imageUrl;

}
