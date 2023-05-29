package com.teste.delivery.api.controller.entregas;

import com.teste.delivery.application.dto.entregas.EntregaDto;
import com.teste.delivery.application.request.entregas.EntregaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EntregaController {

    @PostMapping("/entregas")
    ResponseEntity<Void> save(@RequestBody EntregaRequest entregaRequest,@RequestHeader("authorization") String token);

    @GetMapping("/entregas")
    ResponseEntity<List<EntregaDto>> findAll(@RequestHeader("authorization") String token);

    @GetMapping("/entregas/{id}")
    ResponseEntity<EntregaDto> findEntregaId(@PathVariable Long id,@RequestHeader("authorization") String token);

    @PatchMapping("/entregas/{id}")
    ResponseEntity<EntregaDto> updateEntrega(@PathVariable Long id, @RequestBody EntregaRequest entregaRequest,@RequestHeader("authorization") String token);

    @DeleteMapping("/entregas")
    ResponseEntity<Void> deleteEntrega(@PathVariable Long id,@RequestHeader("authorization") String token);
}
