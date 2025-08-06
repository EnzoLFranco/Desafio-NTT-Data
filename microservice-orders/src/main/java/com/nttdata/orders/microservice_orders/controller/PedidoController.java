package com.nttdata.orders.microservice_orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public PedidoController(WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
    }


}
