package com.teste.delivery.core.domain.services.autenticacoes;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${receita.jwt.expiration}")
    private String expiration;

    @Value("${receita.jwt.secret}")
    private String secret;

    //Metodo para gerar o token
    public String gerarToken(Authentication authentication) {
        Cliente logado = (Cliente) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataexpiration = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Receita")
                .setSubject(String.valueOf(logado.getId()))
                .setIssuedAt(hoje)
                .setExpiration(dataexpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    //Metodo para fazer a validação
    public Boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
