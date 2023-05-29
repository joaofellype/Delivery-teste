package com.teste.delivery.application.request.pedidos;

public class PedidoUpdateRequest {

    private String order;
    private double amount;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
