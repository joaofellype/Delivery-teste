package com.teste.delivery.api.controller.entregas;

import com.teste.delivery.api.shared.BaseControllerImpl;
import com.teste.delivery.application.dto.entregas.EntregaDto;
import com.teste.delivery.application.dto.pedidos.PedidoDto;
import com.teste.delivery.application.request.entregas.EntregaRequest;
import com.teste.delivery.core.domain.agreggates.entrega.Entrega;
import com.teste.delivery.core.domain.services.entregas.EntregaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class EntregaControllerImpl extends BaseControllerImpl<EntregaDto> implements EntregaController {

    private EntregaService entregaService;

    public EntregaControllerImpl(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @Operation(
            summary = "Criação da entrega",
            description = "Operação para criar entrega",
            tags = {
                    "Entregas"
            }
    )
    public ResponseEntity<Void> save(EntregaRequest entregaRequest,String token) {

        Long id =entregaService.create(entregaRequest.getAddress(), entregaRequest.getContact(), entregaRequest.getIdPedido());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).build();

    }

    @Operation(
            summary = "Listar entregas",
            description = "Operação para listar entregas",
            tags = {
                    "Entregas"
            }
    )
    public ResponseEntity<List<EntregaDto>> findAll(String token) {
        List<Entrega> entregas = entregaService.findAll();
        List<EntregaDto> entregaDtos = entregas.stream().map(entrega -> new EntregaDto(entrega.getId(),
                entrega.getAndress(),
                entrega.getContact(),
                entrega.getInitDelivery(),
                PedidoDto.convertPedido(entrega.getPedido()),
                entrega.getFinishDelivery())).collect(Collectors.toList());
        return ResponseEntity.ok(entregaDtos);
    }

    @Operation(
            summary = "Listar entrega por ID",
            description = "Operação para listar entrega por id",
            tags = {
                    "Entregas"
            }
    )
    public ResponseEntity<EntregaDto> findEntregaId(Long id,String token) {
        Entrega entrega = entregaService.findById(id);
        EntregaDto entregaDto = new EntregaDto(entrega.getId(),
                entrega.getAndress(),
                entrega.getContact(),
                entrega.getInitDelivery(),
                PedidoDto.convertPedido(entrega.getPedido()),
                entrega.getFinishDelivery());
        return ResponseEntity.ok(entregaDto);
    }

    @Operation(
            summary = "Alteração de entrega",
            description = "Operação para alterar entrega",
            tags = {
                    "Entregas"
            }
    )
    public ResponseEntity<EntregaDto> updateEntrega(Long id, EntregaRequest entregaRequest,String token) {
        Entrega entrega = entregaService.updateEntrega(id, entregaRequest.getAddress(), entregaRequest.getContact());
        EntregaDto entregaDto = new EntregaDto(entrega.getId(), entrega.getAndress(), entrega.getContact(), entrega.getInitDelivery(), PedidoDto.convertPedido(entrega.getPedido()), entrega.getFinishDelivery());
        return ResponseEntity.ok(entregaDto);
    }

    @Operation(
            summary = "Deletar entrega",
            description = "Operação para deletar entrega",
            tags = {
                    "Entregas"
            }
    )
    public ResponseEntity<Void> deleteEntrega(Long id,String token) {
        entregaService.deleteEntrega(id);
        return ResponseEntity.status(204).build();
    }
}
