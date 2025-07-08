package benicio.iury.BookTrackr.Auth_user.service;

import benicio.iury.BookTrackr.Auth_user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private UserRepository userRespository;

    public AuthService(UserRepository userRespository) {
        this.userRespository = userRespository;
    }

    //Service
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRespository.findByEmail(username);
    }
}
