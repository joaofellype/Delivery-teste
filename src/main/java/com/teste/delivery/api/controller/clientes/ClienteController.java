package com.teste.delivery.api.controller.clientes;

import com.teste.delivery.application.dto.clientes.ClienteDto;
import com.teste.delivery.application.dto.token.TokenDto;
import com.teste.delivery.application.request.clientes.ClienteRequest;
import com.teste.delivery.application.request.clientes.ClienteUpdateRequest;
import com.teste.delivery.application.request.clientes.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClienteController {
    @PostMapping("/clientes")
    ResponseEntity<Void> createCliente(@RequestBody ClienteRequest clienteRequest);

    @GetMapping("/clientes")
    ResponseEntity<List<ClienteDto>> findAll(@RequestHeader("authorization") String token);

    @GetMapping("/clientes/{id}")
    ResponseEntity<ClienteDto> findClienteId(@PathVariable String id,@RequestHeader("authorization") String token);

    @DeleteMapping("/clientes/{id}")
    ResponseEntity<Void> deleteCliente(@PathVariable String id,@RequestHeader("authorization") String token);

    @PatchMapping("/clientes/{id}")
    ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteUpdateRequest clienteUpdateRequest,@RequestHeader("authorization") String token);

    @PostMapping("/clientes/auth")
    ResponseEntity<TokenDto> auth(@RequestBody LoginRequest loginRequest);

}
