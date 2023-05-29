package com.teste.delivery.core.domain.agreggates.pedido;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;
import com.teste.delivery.core.domain.agreggates.entrega.Entrega;
import com.teste.delivery.core.domain.shared.validators.CustomValidator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    @NotEmpty(message = "Pedido is not empty")
    private String pedido;
    @Column
    @NotNull(message = "Amount not Null")
    private double amount;
    @Column
    private LocalDateTime dateOrder;
    @Column
    private String statusPedido;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente cliente;
    @OneToOne(mappedBy = "pedido")

    private Entrega entrega;

    private Pedido(String order, double amount, Cliente client) {
        this.pedido = order;
        this.amount = amount;
        this.cliente = client;
        this.dateOrder = LocalDateTime.now();
        this.statusPedido = StatusPedido.ABERTO.getStatus();
    }

    public Pedido() {
    }

    public static Pedido create(String order, double amount, Cliente client) {
        Pedido pedido = new Pedido(order, amount, client);
        CustomValidator.validateAndThrow(pedido);
        return pedido;

    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public Long getId() {
        return id;
    }

    public String getOrder() {
        return pedido;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }
}
