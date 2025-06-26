package benicio.iury.BookTrackr.Services;

import benicio.iury.BookTrackr.Respositories.UserRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private UserRespository userRespository;

    public AuthService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    //Service
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRespository.findByEmail(username);
    }
}
