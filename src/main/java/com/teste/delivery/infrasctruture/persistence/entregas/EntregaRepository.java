package com.teste.delivery.infrasctruture.persistence.entregas;

import com.teste.delivery.core.domain.agreggates.entrega.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
