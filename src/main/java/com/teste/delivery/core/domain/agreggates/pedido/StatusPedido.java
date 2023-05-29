package com.teste.delivery.core.domain.agreggates.pedido;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StatusPedido {

    ABERTO("Aberto"),
    ANDAMENTO("Em andamento"),
    CAMINHO("A caminho"),
    FECHADO("Concluido");

    private String status;

    StatusPedido(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public static String searchValue(String value){

       StatusPedido[] status = StatusPedido.values();
        List<StatusPedido> lista = Arrays.stream(status).filter(statusPedido -> statusPedido.getStatus().equals(value)).collect(Collectors.toList());
        if(lista.isEmpty())
            return null;

        return lista.get(0).getStatus();
    }
}
