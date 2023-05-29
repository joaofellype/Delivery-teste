package com.teste.delivery.application.dto.clientes;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;

public class ClienteDto {

    private Long id;
    private String name;
    private String email;
    private String contact;

    public ClienteDto(Long id, String name, String email, String contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public static ClienteDto convertDto(Cliente cliente) {

        return new ClienteDto(cliente.getId(), cliente.getName(), cliente.getEmail(), cliente.getContact());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
