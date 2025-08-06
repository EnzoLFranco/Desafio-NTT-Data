package com.nttdata.orders.microservice_orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private String cliente;
    private List<ItemPedidoDTO> itens;
}
