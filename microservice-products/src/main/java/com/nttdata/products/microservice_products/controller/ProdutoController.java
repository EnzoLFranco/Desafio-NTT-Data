package com.nttdata.products.microservice_products.controller;

import com.nttdata.products.microservice_products.model.Produto;
import com.nttdata.products.microservice_products.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto){
        Produto savedProduto = produtoRepository.save(produto);
        return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

}
