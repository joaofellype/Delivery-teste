package com.teste.delivery.core.domain.services.autenticacoes;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;
import com.teste.delivery.core.domain.shared.exceptions.DomainException;
import com.teste.delivery.infrasctruture.persistence.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticationService implements UserDetailsService {
    @Autowired
    private ClienteRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Cliente> cliente = repository.findByEmail(username);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new DomainException("Dados inválidos");
    }
}
