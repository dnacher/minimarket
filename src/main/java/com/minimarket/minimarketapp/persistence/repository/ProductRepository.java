package com.minimarket.minimarketapp.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.minimarket.minimarketapp.persistence.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
