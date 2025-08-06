package com.nttdata.orders.microservice_orders.model;

import lombok.Data;

@Data
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
}
