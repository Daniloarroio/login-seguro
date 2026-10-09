package com.danilo.loginseguro.controller;

import com.danilo.loginseguro.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioAdminController {

    private final UsuarioService usuarioService;

    public UsuarioAdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios";
    }

    @PostMapping("/usuarios")
    public String atualizarRole(
            @RequestParam String email,
            @RequestParam(required = false) String role,
            RedirectAttributes redirectAttributes) {

        try {
            usuarioService.atualizarRolePorAdmin(email, role);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Tipo de usuário atualizado com sucesso."
            );

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );
        }

        return "redirect:/usuarios";
    }
}