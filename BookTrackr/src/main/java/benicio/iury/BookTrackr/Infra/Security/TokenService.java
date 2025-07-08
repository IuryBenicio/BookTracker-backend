package benicio.iury.BookTrackr.Infra.Security;

import benicio.iury.BookTrackr.Auth_user.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${SECRET_KEY}")
    private String secret;

    public String generateToken(UserEntity user){
        try{
            Algorithm algotitimo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("BookTracker")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algotitimo);

            return token;
        }catch(JWTCreationException error){
            throw new RuntimeException("Error while generating token", error);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algotitimo = Algorithm.HMAC256(secret);
            return JWT.require(algotitimo)
                    .withIssuer("BookTracker")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTCreationException error){
            return "";
        }
    }

    private Instant genExpirationDate(){ //gera uma data de expiração
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); //duas horas de expiração
    }

}
