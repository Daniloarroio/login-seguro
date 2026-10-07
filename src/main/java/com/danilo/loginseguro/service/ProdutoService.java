package com.danilo.loginseguro.service;

import com.danilo.loginseguro.model.Produto;
import com.danilo.loginseguro.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(String nome, String descricao, Double valor) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero.");
        }

        Produto produto = new Produto();
        produto.setNome(nome.trim());
        produto.setDescricao(descricao);
        produto.setValor(valor);

        return produtoRepository.save(produto);
    }

    public Produto editar(String id, String nome, String descricao, Double valor) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero.");
        }

        produto.setNome(nome.trim());
        produto.setDescricao(descricao);
        produto.setValor(valor);

        return produtoRepository.save(produto);
    }

    public void excluir(String id) {

        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        produtoRepository.deleteById(id);
    }
}