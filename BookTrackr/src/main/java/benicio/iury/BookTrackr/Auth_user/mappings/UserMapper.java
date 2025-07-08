package benicio.iury.BookTrackr.Auth_user.mappings;

import benicio.iury.BookTrackr.Auth_user.entities.UserEntity;
import benicio.iury.BookTrackr.Auth_user.DTO.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity entity);
    UserEntity toEntity(UserDTO dto);
}
