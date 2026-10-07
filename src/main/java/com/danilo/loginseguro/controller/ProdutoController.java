package com.danilo.loginseguro.controller;

import com.danilo.loginseguro.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produtos")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String descricao,
            @RequestParam Double valor) {

        produtoService.cadastrar(nome, descricao, valor);

        return "redirect:/home";
    }
}