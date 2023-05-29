package com.teste.delivery.core.domain.services.clientes;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;

import java.util.List;

public interface ClienteService {

    Long create(String name, String email, String contact, String password);

    Cliente getUserforId(Long id);

    List<Cliente> getClientes();

    Cliente updateCliente(Long id, String name, String email, String contact);

    void deleteCliente(Long id);
}
