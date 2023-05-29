package com.teste.delivery.core.domain.agreggates.clientes;

import com.teste.delivery.core.domain.agreggates.pedido.Pedido;
import com.teste.delivery.core.domain.shared.validators.CustomValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    @NotEmpty(message = "Name is not empty")
    private String name;
    @Column
    @NotNull
    @NotEmpty(message = "Email is not empty")
    private String email;
    @Column
    @NotNull
    @NotEmpty(message = "Contact is not empty")
    private String contact;
    @Column
    @NotNull
    @NotEmpty(message = "Password is not empty")
    private String password;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, targetEntity = Pedido.class)
    private List<Pedido> pedidos;

    public Cliente() {
    }

    private Cliente(String name, String email, String contact, String password) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    public static Cliente create(String name, String email, String contact, String password) {

        Cliente cliente = new Cliente(name, email, contact, password);

        CustomValidator.validateAndThrow(cliente);
        return cliente;
    }

    public Long getId() {
        return id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
