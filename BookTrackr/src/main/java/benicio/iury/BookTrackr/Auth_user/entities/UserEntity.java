package benicio.iury.BookTrackr.Auth_user.entities;

import java.util.Collection;
import java.util.List;

import benicio.iury.BookTrackr.Book.entities.BookEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id")
    private long id;

    @NotBlank(message = "campo obrigatório")
    @Column(name="nome")
    private String name;

    @NotBlank(message = "campo obrigatório")
    @Email
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

    //CONSTRUTOR DO UserEntity
    public UserEntity(String name,String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
