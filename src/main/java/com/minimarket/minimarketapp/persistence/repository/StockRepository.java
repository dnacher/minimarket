package com.minimarket.minimarketapp.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.minimarket.minimarketapp.persistence.model.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {

    Stock findStocksByProduct_Id(Integer id);
}
