package com.teste.delivery.infrasctruture.persistence.pedidos;

import com.teste.delivery.core.domain.agreggates.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE pedido p SET p.statusPedido = :statusPedido WHERE p.id = :id",nativeQuery = true)
    void updateStatusPedido(@Param("statusPedido") String statusPedido,@Param("id") Long id);
}
