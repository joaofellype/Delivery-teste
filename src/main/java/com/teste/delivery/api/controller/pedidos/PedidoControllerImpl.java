package com.teste.delivery.api.controller.pedidos;

import com.teste.delivery.api.shared.BaseControllerImpl;
import com.teste.delivery.application.dto.clientes.ClienteDto;
import com.teste.delivery.application.dto.pedidos.PedidoDto;
import com.teste.delivery.application.request.pedidos.PedidoRequest;
import com.teste.delivery.application.request.pedidos.PedidoStatusRequest;
import com.teste.delivery.application.request.pedidos.PedidoUpdateRequest;
import com.teste.delivery.core.domain.agreggates.pedido.Pedido;
import com.teste.delivery.core.domain.services.autenticacoes.TokenService;
import com.teste.delivery.core.domain.services.pedidos.PedidoService;
import com.teste.delivery.infrasctruture.converters.DateFormatter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class PedidoControllerImpl extends BaseControllerImpl<PedidoDto> implements PedidoController {


    private PedidoService pedidoService;
    private TokenService tokenService;

    public PedidoControllerImpl(PedidoService pedidoService, TokenService tokenService) {
        this.pedidoService = pedidoService;
        this.tokenService = tokenService;
    }

    @Operation(
            summary = "Criação de pedido",
            description = "Operação para criar pedido",
            tags = {
                    "Pedidos"
            }
    )
    public ResponseEntity<Void> createPedido(PedidoRequest pedidoRequest, String token) {
        String tokenSplit = token.split(" ")[1];

        Long idCliente = tokenService.getIdUsuario(tokenSplit);
        Long id =pedidoService.create(pedidoRequest.getOrder(), pedidoRequest.getAmount(), idCliente);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).build();
    }

    @Operation(
            summary = "Listar pedido por Id",
            description = "Operação para listar pedido por Id",
            tags = {
                    "Pedidos"
            }
    )
    public ResponseEntity<PedidoDto> findPedidoForId(Long id, String token) {
        Pedido pedido = pedidoService.getPedidoForId(id);
        ClienteDto clienteDto = new ClienteDto(pedido.getCliente().getId(),
                pedido.getCliente().getName(),
                pedido.getCliente().getEmail(),
                pedido.getCliente().getContact());
        PedidoDto pedidoDto = new PedidoDto(pedido.getId(),
                pedido.getPedido(),
                pedido.getAmount(),
                DateFormatter.toString(pedido.getDateOrder()),
                clienteDto);
        return ResponseEntity.ok(pedidoDto);
    }

    @Operation(
            summary = "Listar todos pedidos",
            description = "Operação para listar todos pedidos",
            tags = {
                    "Pedidos"
            }
    )
    public ResponseEntity<List<PedidoDto>> findAll(String token) {

        List<Pedido> pedidos = pedidoService.getAll();
        List<PedidoDto> pedidoDtos = pedidos.stream()
                .map(pedido -> new PedidoDto(pedido.getId(),
                        pedido.getPedido(),
                        pedido.getAmount(),
                        DateFormatter.toString(pedido.getDateOrder()),
                        ClienteDto.convertDto(pedido.getCliente()))).collect(Collectors.toList());


        return ResponseEntity.ok(pedidoDtos);
    }

    @Operation(
            summary = "Deletar pedido",
            description = "Operação para deletar pedido",
            tags = {
                    "Pedidos"
            }
    )
    public ResponseEntity<Void> deletePedido(Long id,String token) {
        pedidoService.deletePedido(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Alteração de pedido",
            description = "Operação para alterar pedido",
            tags = {
                    "Pedidos"
            }
    )
    public ResponseEntity<PedidoDto> updatePedido(Long id, PedidoUpdateRequest pedidoUpdateRequest,String token) {
        Pedido pedido = pedidoService.updatePedido(id, pedidoUpdateRequest.getOrder(), pedidoUpdateRequest.getAmount());
        PedidoDto pedidoDto = new PedidoDto(pedido.getId(),
                pedido.getOrder(),
                pedido.getAmount(),
                DateFormatter.toString(pedido.getDateOrder()),
                ClienteDto.convertDto(pedido.getCliente()));


        return ResponseEntity.ok(pedidoDto);
    }

    @Operation(
            summary = "Criação de pedido",
            description = "Operação para criar pedido",
            tags = {
                    "Pedidos"
            }
    )
    public ResponseEntity<Void> updateStatus(Long id, PedidoStatusRequest pedidoStatusRequest,String token) {
        pedidoService.updateStatus(pedidoStatusRequest.getStatus(),id);
        return ResponseEntity.ok().build();
    }
}
