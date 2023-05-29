package com.teste.delivery.core.domain.services.entregas;

import com.teste.delivery.core.domain.agreggates.entrega.Entrega;

import java.util.List;

public interface EntregaService {

    Long create(String andress, String contact, Long idPedido);

    List<Entrega> findAll();

    Entrega findById(Long id);

    Entrega updateEntrega(Long idEntrega, String andress, String contact);

    void deleteEntrega(Long id);
}
