package com.danilo.loginseguro.repository;

import com.danilo.loginseguro.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
}