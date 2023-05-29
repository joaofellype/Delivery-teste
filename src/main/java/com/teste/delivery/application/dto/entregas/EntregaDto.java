package com.teste.delivery.application.dto.entregas;

import com.teste.delivery.application.dto.pedidos.PedidoDto;

import java.time.LocalDateTime;

public class EntregaDto {

    private Long id;
    private String andress;
    private String contact;
    private LocalDateTime initDelivery;
    private PedidoDto pedidoDto;
    private LocalDateTime closeDelivery;

    public EntregaDto(Long id, String andress, String contact, LocalDateTime initDelivery, PedidoDto pedidoDto, LocalDateTime closeDelivery) {
        this.id = id;
        this.andress = andress;
        this.contact = contact;
        this.initDelivery = initDelivery;
        this.pedidoDto = pedidoDto;
        this.closeDelivery = closeDelivery;
    }

    public String getAndress() {
        return andress;
    }

    public void setAndress(String andress) {
        this.andress = andress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setInitDelivery(LocalDateTime initDelivery) {
        this.initDelivery = initDelivery;
    }

    public PedidoDto getPedidoDto() {
        return pedidoDto;
    }

    public void setPedidoDto(PedidoDto pedidoDto) {
        this.pedidoDto = pedidoDto;
    }

    public LocalDateTime getCloseDelivery() {
        return closeDelivery;
    }

    public void setCloseDelivery(LocalDateTime closeDelivery) {
        this.closeDelivery = closeDelivery;
    }
}
