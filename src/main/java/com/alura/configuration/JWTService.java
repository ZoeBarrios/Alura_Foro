package com.alura.configuration;

import com.alura.modelo.Usuario;
import com.alura.repositorys.UsuarioRepository;
import com.alura.services.UsuarioService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class JWTService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Value("${api.secreto}")
    private String secret;

    public Authentication validarToken(String token) {


            DecodedJWT verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("foro alura")
                    .build()
                    .verify(token);

            var usuario=usuarioRepository.findBynombre(verifier.getSubject());
            Authentication authentication=new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            return  authentication;

    }

    public String generarToken( Authentication credenciales) {

        Usuario usuario= (Usuario) credenciales.getPrincipal();
            return JWT.create()
                    .withIssuer("foro alura")
                    .withSubject(usuario.getNombre())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFecha())
                    .sign(Algorithm.HMAC256(secret));

    }





    private Instant generarFecha(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
