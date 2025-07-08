package benicio.iury.BookTrackr.Auth_user.controller.UserControllers;

import benicio.iury.BookTrackr.Auth_user.mappings.UserMapper;
import benicio.iury.BookTrackr.DTOS.LoginDTO;
import benicio.iury.BookTrackr.DTOS.ResAndReq.LoginResponseDTO;
import benicio.iury.BookTrackr.DTOS.RegisterDTO;
import benicio.iury.BookTrackr.Auth_user.entities.UserEntity;
import benicio.iury.BookTrackr.Auth_user.DTO.UserDTO;
import benicio.iury.BookTrackr.Infra.Security.TokenService;
import benicio.iury.BookTrackr.Auth_user.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;
    
    private final UserRepository userRespository;

    public AuthController(PasswordEncoder passwordEncoder, UserMapper userMapper, TokenService tokenService, AuthenticationManager authenticationManager, UserRepository userRespository) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userRespository = userRespository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity)/*cast*/ auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        //verifica se o usuário já existe
        if (this.userRespository.findByEmail(registerDTO.email())!= null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existente");
        }

        if (this.userRespository.findByName(registerDTO.name())!= null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já existente");
        }


        String criptPassword = passwordEncoder.encode(registerDTO.password());
        UserEntity newUser = new UserEntity(registerDTO.name(),registerDTO.email(), criptPassword);
        UserDTO userDTO = userMapper.toDTO(userRespository.save(newUser));

        return ResponseEntity.ok(userDTO);
    }
}
