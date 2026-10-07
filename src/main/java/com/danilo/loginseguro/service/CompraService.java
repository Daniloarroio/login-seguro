package com.danilo.loginseguro.service;

import com.danilo.loginseguro.model.Compra;
import com.danilo.loginseguro.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public Compra realizarCompra(String emailUsuario, List<String> produtos, Double valorTotal) {

        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Selecione pelo menos um produto.");
        }

        if (valorTotal == null || valorTotal <= 0) {
            throw new IllegalArgumentException("O valor da compra deve ser maior que zero.");
        }

        Compra compra = new Compra(
                emailUsuario,
                produtos,
                valorTotal
        );

        return compraRepository.save(compra);
    }
}