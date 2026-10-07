package com.danilo.loginseguro.repository;

import com.danilo.loginseguro.model.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompraRepository extends MongoRepository<Compra, String> {
}