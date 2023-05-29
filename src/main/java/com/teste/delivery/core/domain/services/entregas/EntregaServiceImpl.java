package com.teste.delivery.core.domain.services.entregas;

import com.teste.delivery.core.domain.agreggates.entrega.Entrega;
import com.teste.delivery.core.domain.agreggates.pedido.Pedido;
import com.teste.delivery.core.domain.services.pedidos.PedidoService;
import com.teste.delivery.infrasctruture.persistence.entregas.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaServiceImpl implements EntregaService {
    private EntregaRepository entregaRepository;
    private PedidoService pedidoService;

    public EntregaServiceImpl(EntregaRepository entregaRepository, PedidoService pedidoService) {
        this.entregaRepository = entregaRepository;
        this.pedidoService = pedidoService;
    }

    @Override
    public Long create(String andress, String contact, Long idPedido) {


        Pedido pedido = pedidoService.getPedidoForId(idPedido);
        Entrega entrega = Entrega.create(andress, contact, pedido);

       return entregaRepository.save(entrega).getId();

    }

    @Override
    public List<Entrega> findAll() {

        return entregaRepository.findAll();
    }

    @Override
    public Entrega findById(Long id) {
        return entregaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado entrega"));
    }

    @Override
    public Entrega updateEntrega(Long idEntrega, String andress, String contact) {
        Entrega entrega = findById(idEntrega);
        entrega.setAndress(andress == null ? entrega.getAndress() : andress);
        entrega.setContact(contact == null ? entrega.getContact() : contact);

        return entregaRepository.save(entrega);
    }

    @Override
    public void deleteEntrega(Long id) {
        entregaRepository.deleteById(id);

    }
}
