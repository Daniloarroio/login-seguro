package com.danilo.loginseguro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "compras")
public class Compra {

    @Id
    private String id;

    private String emailUsuario;
    private List<String> produtos;
    private Double valorTotal;

    public Compra() {
    }

    public Compra(String emailUsuario, List<String> produtos, Double valorTotal) {
        this.emailUsuario = emailUsuario;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}