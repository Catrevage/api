package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.user.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token}")
	private String secret;
	
	public String gerartoken(Usuario usuario) {
		try {
		    var algoritmo = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API Voll.med")
		        .withSubject(usuario.getLogin())
		        .withExpiresAt(dataExpiracao())
		        .sign(algoritmo);
		   
		} catch (JWTCreationException exception){
		   throw new RuntimeException("Erro ao gerar JWT", exception);
		}
	}

	public String getSubejct(String tokenJWT) {
		
		try {
		    var algoritmo = Algorithm.HMAC256(secret);
		    return JWT.require(algoritmo)
		        .withIssuer("API Voll.med")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();        
		   
		} catch (JWTVerificationException exception){
		   throw new RuntimeException("Token JWT inválido ou expirado!");
		}
	}
	
	private Instant dataExpiracao() {		
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
