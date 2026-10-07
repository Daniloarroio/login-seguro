package com.danilo.loginseguro.controller;

import com.danilo.loginseguro.service.CompraService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/compras")
    public String realizarCompra(
            @RequestParam List<String> produtos,
            @RequestParam Double valorTotal,
            Authentication authentication) {

        String emailUsuario = authentication.getName();

        compraService.realizarCompra(
                emailUsuario,
                produtos,
                valorTotal
        );

        return "redirect:/home";
    }
}