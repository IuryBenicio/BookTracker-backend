package benicio.iury.BookTrackr.Mappings;

import org.springframework.stereotype.Component;

import benicio.iury.BookTrackr.DTOS.UserDTO;
import benicio.iury.BookTrackr.Entities.UserEntity;

@Component
public class UserMapper {
    
    public UserEntity toModel(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();

//         userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setBooks(userDTO.getBooks());
        userEntity.setImageUrl(userDTO.getImageUrl());

        return userEntity;
    }

    public UserDTO toDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();

//         userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setBooks(userEntity.getBooks());
        userDTO.setImageUrl(userEntity.getImageUrl());

        return userDTO;
    }

}
