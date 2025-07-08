package benicio.iury.BookTrackr.Auth_user.service;

import java.util.Optional;

import benicio.iury.BookTrackr.Auth_user.mappings.UserMapper;
import benicio.iury.BookTrackr.Auth_user.DTO.UserDTO;
import org.springframework.stereotype.Service;

import benicio.iury.BookTrackr.Auth_user.entities.UserEntity;
import benicio.iury.BookTrackr.Auth_user.repositories.UserRepository;

@Service
public class UserService {
    //Dependecias
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //Services
    public UserDTO Register(UserEntity user){
        UserEntity userSave = userRepository.save(user);
        UserDTO userDTO = userMapper.toDTO(userSave);
        return userDTO;
    }

    public UserDTO alterarUser(long id, String name, String email){
        Optional<UserEntity> OptionalUser = userRepository.findById(id);
        if(!OptionalUser.isPresent()){
            return null;
        }

        UserEntity userDB = OptionalUser.get();

        if(userDB.getName().equals(name) & userDB.getEmail().equals(email)){
            return null;
        }

        userDB.setName(name);
        userDB.setEmail(email);

        return userMapper.toDTO(userDB);
    }

}
