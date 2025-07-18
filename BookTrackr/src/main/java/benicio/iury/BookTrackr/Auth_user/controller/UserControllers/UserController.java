package benicio.iury.BookTrackr.Auth_user.controller.UserControllers;

import benicio.iury.BookTrackr.Auth_user.DTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import benicio.iury.BookTrackr.DTOS.ResAndReq.UpdateUserDTO;
import benicio.iury.BookTrackr.Auth_user.repositories.UserRepository;
import benicio.iury.BookTrackr.Auth_user.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRespository;
    private final UserService userService;

    public UserController(UserService userService, UserRepository userRespository) {
        this.userService = userService;
        this.userRespository = userRespository;
    }

    //Controllers
    @PostMapping("/edit/{id}")
    public ResponseEntity<?> alterarUser(@PathVariable long id, @RequestBody UpdateUserDTO userBody){
        UserDTO response = userService.alterarUser(id, userBody.getName(), userBody.getEmail());
        if(response == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tivemos um erro ao alterar usuário");
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarUser(@PathVariable long id){
        boolean userExists = userRespository.existsById(id);
        if(userExists){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
        }

        try{
            userRespository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Tivemos problemas ao deletar seu usuário" + e.getMessage());
        }
    }
}
