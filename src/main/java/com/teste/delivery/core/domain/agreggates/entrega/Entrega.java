package com.teste.delivery.core.domain.agreggates.entrega;

import com.teste.delivery.core.domain.agreggates.pedido.Pedido;
import com.teste.delivery.core.domain.shared.validators.CustomValidator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    @NotEmpty(message = "Adress is not empty")
    private String andress;
    @Column
    @NotNull
    @NotEmpty(message = "Contact is not empty")
    private String contact;
    @Column
    private LocalDateTime initDelivery;
    @Column
    private LocalDateTime finishDelivery;
    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Entrega() {
    }

    private Entrega(String adress, String contact, Pedido pedido) {
        this.contact = contact;
        this.andress = adress;
        this.initDelivery = LocalDateTime.now();
        this.pedido = pedido;
    }

    public static Entrega create(String adress, String contact, Pedido pedido) {
        Entrega entrega = new Entrega(adress, contact, pedido);
        CustomValidator.validateAndThrow(entrega);
        return entrega;
    }

    public Long getId() {
        return id;
    }

    public String getAndress() {
        return andress;
    }

    public void setAndress(String andress) {
        this.andress = andress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDateTime getInitDelivery() {
        return initDelivery;
    }

    public LocalDateTime getFinishDelivery() {
        return finishDelivery;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
