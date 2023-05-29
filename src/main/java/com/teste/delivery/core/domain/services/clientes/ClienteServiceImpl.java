package com.teste.delivery.core.domain.services.clientes;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;
import com.teste.delivery.core.domain.shared.exceptions.DomainException;
import com.teste.delivery.core.domain.shared.exceptions.NotFoundException;
import com.teste.delivery.infrasctruture.persistence.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Long create(String name, String email, String contact, String password) {
        Optional<Cliente> clienteVerificacao = clienteRepository.findByEmail(email);
        if(clienteVerificacao.isPresent()){
            throw new DomainException("Cliente is exist");
        }

        Cliente cliente = Cliente.create(name, email, contact, password);
        cliente.setPassword(passwordEncoder.encode(password));

        Cliente clienteCreate = clienteRepository.save(cliente);
        return clienteCreate.getId();
    }

    @Override
    public Cliente getUserforId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente updateCliente(Long id, String name, String email, String contact) {


        Cliente cliente = getUserforId(id);
        cliente.setContact(contact == null ? cliente.getContact() : contact);
        cliente.setName(name == null ? cliente.getContact() : name);
        cliente.setEmail(email == null ? cliente.getEmail() : email);

        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
