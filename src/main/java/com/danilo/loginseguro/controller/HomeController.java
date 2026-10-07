package com.danilo.loginseguro.controller;

import com.danilo.loginseguro.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProdutoRepository produtoRepository;

    public HomeController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "home";
    }
}