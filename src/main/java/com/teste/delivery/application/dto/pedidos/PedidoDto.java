package com.teste.delivery.application.dto.pedidos;

import com.teste.delivery.application.dto.clientes.ClienteDto;
import com.teste.delivery.core.domain.agreggates.pedido.Pedido;
import com.teste.delivery.infrasctruture.converters.DateFormatter;

public class PedidoDto {

    private Long id;
    private String order;
    private double amount;
    private String datePedido;
    private ClienteDto cliente;

    public PedidoDto(Long id, String order, double amount, String datePedido, ClienteDto cliente) {
        this.id = id;
        this.order = order;
        this.amount = amount;
        this.datePedido = datePedido;
        this.cliente = cliente;
    }

    public static PedidoDto convertPedido(Pedido pedido) {

        return new PedidoDto(pedido.getId(),
                pedido.getPedido(),
                pedido.getAmount(),
                DateFormatter.toString(pedido.getDateOrder()),
                ClienteDto.convertDto(pedido.getCliente()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDatePedido() {
        return datePedido;
    }

    public void setDatePedido(String datePedido) {
        this.datePedido = datePedido;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }
}
