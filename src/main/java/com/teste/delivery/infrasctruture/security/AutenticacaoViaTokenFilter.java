package com.teste.delivery.infrasctruture.security;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;
import com.teste.delivery.core.domain.services.autenticacoes.TokenService;
import com.teste.delivery.infrasctruture.persistence.clientes.ClienteRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private ClienteRepository repository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, ClienteRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        Boolean valido = tokenService.isTokenValido(token);
        if (valido) {
            autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);

    }

    private void autenticarUsuario(String token) {
        Long idCliente = tokenService.getIdUsuario(token);
        Cliente cliente = repository.findById(idCliente).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());

    }
}
