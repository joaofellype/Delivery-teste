package com.teste.delivery.api.controller.clientes;

import com.teste.delivery.api.shared.BaseControllerImpl;
import com.teste.delivery.application.dto.clientes.ClienteDto;
import com.teste.delivery.application.dto.token.TokenDto;
import com.teste.delivery.application.request.clientes.ClienteRequest;
import com.teste.delivery.application.request.clientes.ClienteUpdateRequest;
import com.teste.delivery.application.request.clientes.LoginRequest;
import com.teste.delivery.core.domain.agreggates.clientes.Cliente;
import com.teste.delivery.core.domain.services.autenticacoes.TokenService;
import com.teste.delivery.core.domain.services.clientes.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class ClienteControllerImpl extends BaseControllerImpl<ClienteDto> implements ClienteController {

    private ClienteService clienteService;

    private AuthenticationManager authManager;
    private TokenService tokenService;

    public ClienteControllerImpl(ClienteService clienteService, AuthenticationManager authManager,TokenService tokenService) {
        this.clienteService = clienteService;
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @Operation(
            summary = "Criação de clientes",
            description = "Operação para criar cliente",
            tags = {
                    "Clientes"
            }
    )
    public ResponseEntity<Void> createCliente(ClienteRequest clienteRequest) {
        Long id = clienteService.create(clienteRequest.getName(),
                clienteRequest.getEmail(),
                clienteRequest.getContact(),
                clienteRequest.getPassword());

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).build();
    }

    @Operation(
            summary = "Listar clientes",
            description = "Operação para listar clientes",
            tags = {
                    "Clientes"
            }
    )
    public ResponseEntity<List<ClienteDto>> findAll(String token) {
        List<ClienteDto> clientes = clienteService.getClientes().stream().map(cliente ->
                new ClienteDto(cliente.getId(),
                        cliente.getName(),
                        cliente.getEmail(),
                        cliente.getContact())).collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }
    @Operation(
            summary = "Listar cliente por id",
            description = "Operação para listar cliente por id",
            tags = {
                    "Clientes"
            }
    )
    public ResponseEntity<ClienteDto> findClienteId(String id,String token) {
        Cliente cliente = clienteService.getUserforId(Long.parseLong(id));
        ClienteDto clienteDto = new ClienteDto(cliente.getId(), cliente.getName(), cliente.getEmail(), cliente.getContact());
        return ResponseEntity.ok(clienteDto);
    }
    @Operation(
            summary = "Listar clientes",
            description = "Operação para listar clientes",
            tags = {
                    "Clientes"
            }
    )

    public ResponseEntity<Void> deleteCliente(String id,String token) {
        clienteService.deleteCliente(Long.parseLong(id));
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Alterar cliente",
            description = "Operação para alterar cliente",
            tags = {
                    "Clientes"
            }
    )
    public ResponseEntity<ClienteDto> updateCliente(Long id, ClienteUpdateRequest clienteUpdateRequest,String token) {
        Cliente cliente = clienteService.updateCliente(id, clienteUpdateRequest.getName()
                , clienteUpdateRequest.getEmail()
                , clienteUpdateRequest.getContact());
        ClienteDto clienteDto = new ClienteDto(cliente.getId(), cliente.getName(), cliente.getEmail(), cliente.getContact());
        return ResponseEntity.ok(clienteDto);
    }

    @Operation(
            summary = "Autenticação de cliente",
            description = "Operação para autenticação",
            tags = {
                    "Autenticação"
            }
    )
    public ResponseEntity<TokenDto> auth(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto("Bearer", token, "86400000"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
