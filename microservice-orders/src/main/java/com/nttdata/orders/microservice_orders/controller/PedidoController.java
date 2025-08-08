package com.nttdata.orders.microservice_orders.controller;

import com.nttdata.orders.microservice_orders.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final WebClient webClient;

    @Autowired
    public PedidoController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://microservice-products").build();
    }

    @PostMapping
    public Mono<ResponseEntity<String>> criarPedido(@RequestBody PedidoDTO pedido) {
        return this.webClient.get()
                .uri("/produtos/{id}", pedido.getItens().get(0).getIdProduto())
                .retrieve()
                .bodyToMono(ProdutoDTO.class)
                .map(produto -> {
                    System.out.println("Pedido recebido para o cliente: " + pedido.getCliente());
                    System.out.println("Item do pedido: " + produto.getNome() + ", quantidade: " + pedido.getItens().get(0).getQuantidade());
                    return ResponseEntity.ok("Pedido para " + pedido.getCliente() + " simulado com sucesso!");
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
