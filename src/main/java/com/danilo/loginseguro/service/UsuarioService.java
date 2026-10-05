package com.danilo.loginseguro.service;

import com.danilo.loginseguro.model.Usuario;
import com.danilo.loginseguro.repository.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(
            String nome,
            String email,
            String senha) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome é obrigatório.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O e-mail é obrigatório.");
        }

        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException(
                    "A senha deve ter pelo menos 8 caracteres.");
        }

        String emailNormalizado =
                email.trim().toLowerCase();

        if (usuarioRepository.existsByEmail(emailNormalizado)) {
            throw new IllegalArgumentException(
                    "Este e-mail já está cadastrado.");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(nome.trim());
        usuario.setEmail(emailNormalizado);

        // Criptografa a senha
        usuario.setSenha(
                passwordEncoder.encode(senha)
        );

        // Todo cadastro público começa como usuário comum
        usuario.setRoles(
                Set.of("ROLE_USER")
        );

        return usuarioRepository.save(usuario);
    }
}