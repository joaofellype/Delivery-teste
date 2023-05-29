package com.teste.delivery.core.domain.services.pedidos;

import com.teste.delivery.core.domain.agreggates.pedido.Pedido;

import java.util.List;

public interface PedidoService {

    Long create(String order, double amount, Long idCliente);

    Pedido getPedidoForId(Long id);

    List<Pedido> getAll();

    Pedido updatePedido(Long id, String order, double amount);

    void deletePedido(Long id);

    void updateStatus(String status,Long id);

}
