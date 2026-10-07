package com.danilo.loginseguro.service;

import com.danilo.loginseguro.model.Usuario;
import com.danilo.loginseguro.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        System.out.println("E-mail recebido no login: " + email);

        Usuario usuario = usuarioRepository.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        System.out.println("Usuário encontrado no banco: " + usuario.getEmail());

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(
                    usuario.getRoles()
                        .stream()
                        .map(role -> role.replace("ROLE_", ""))
                        .toArray(String[]::new)
                )
                .build();
    }
}