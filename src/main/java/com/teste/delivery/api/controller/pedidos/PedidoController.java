package com.teste.delivery.api.controller.pedidos;

import com.teste.delivery.application.dto.pedidos.PedidoDto;
import com.teste.delivery.application.request.pedidos.PedidoRequest;
import com.teste.delivery.application.request.pedidos.PedidoStatusRequest;
import com.teste.delivery.application.request.pedidos.PedidoUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PedidoController {

    @PostMapping("/pedidos")
    ResponseEntity<Void> createPedido(@RequestBody PedidoRequest pedidoRequest, @RequestHeader("authorization") String token);

    @GetMapping("/pedidos/{id}")
    ResponseEntity<PedidoDto> findPedidoForId(@PathVariable Long id, @RequestHeader("authorization") String token);

    @GetMapping("/pedidos")
    ResponseEntity<List<PedidoDto>> findAll(@RequestHeader("authorization") String token);

    @DeleteMapping("/pedidos/{id}")
    ResponseEntity<Void> deletePedido(@PathVariable Long id, @RequestHeader("authorization") String token);

    @PatchMapping("/pedidos/{id}")
    ResponseEntity<PedidoDto> updatePedido(@PathVariable Long id, @RequestBody PedidoUpdateRequest pedidoUpdateRequest, @RequestHeader("authorization") String token);
    @PatchMapping("/pedidos/status/{id}")
    ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody PedidoStatusRequest pedidoStatusRequest, @RequestHeader("authorization") String token);
}
