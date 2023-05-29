package com.teste.delivery.core.domain.services.pedidos;

import com.teste.delivery.core.domain.agreggates.clientes.Cliente;
import com.teste.delivery.core.domain.agreggates.pedido.Pedido;
import com.teste.delivery.core.domain.agreggates.pedido.StatusPedido;
import com.teste.delivery.core.domain.services.clientes.ClienteService;
import com.teste.delivery.core.domain.shared.exceptions.DomainException;
import com.teste.delivery.infrasctruture.persistence.pedidos.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteService ClienteService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteService ClienteService) {
        this.pedidoRepository = pedidoRepository;
        this.ClienteService = ClienteService;
    }

    @Override
    public Long create(String order, double amount, Long idCliente) {

        Cliente cliente = ClienteService.getUserforId(idCliente);
        Pedido pedido = Pedido.create(order, amount, cliente);
        return pedidoRepository.save(pedido).getId();
    }

    @Override
    public Pedido getPedidoForId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));
    }

    @Override
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido updatePedido(Long id, String order, double amount) {

        Pedido pedido = getPedidoForId(id);

        pedido.setPedido(order == null ? pedido.getPedido() : order);
        pedido.setAmount(amount == 0 ? pedido.getAmount() : amount);


        return pedidoRepository.save(pedido);
    }

    @Override
    public void deletePedido(Long id) {

        pedidoRepository.deleteById(id);
    }

    @Override
    public void updateStatus(String status,Long id) {
        Pedido pedido = getPedidoForId(id);
        String statusEnum = StatusPedido.searchValue(status);
        if(statusEnum == null){
            throw  new DomainException("Status not defined");
        }
        pedido.setStatusPedido(statusEnum);
        pedidoRepository.save(pedido);

    }
}
