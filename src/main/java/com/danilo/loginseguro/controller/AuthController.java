package com.danilo.loginseguro.controller;

import com.danilo.loginseguro.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
public String cadastro() {
    return "cadastro";
}

    @PostMapping("/cadastro")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String confirmarSenha,
            RedirectAttributes redirectAttributes) {
                System.out.println("========== CADASTRO ==========");
System.out.println("Nome: " + nome);
System.out.println("Email: " + email);
System.out.println("Senha: " + senha);
System.out.println("Confirmar senha: " + confirmarSenha);
System.out.println("==============================");

        if (!senha.equals(confirmarSenha)) {
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "As senhas não coincidem."
            );

            return "redirect:/cadastro";
        }

        try {

            usuarioService.cadastrar(
                    nome,
                    email,
                    senha
            );

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Cadastro realizado! Faça seu login."
            );

            return "redirect:/login";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );

            return "redirect:/cadastro";
        }
    }
}