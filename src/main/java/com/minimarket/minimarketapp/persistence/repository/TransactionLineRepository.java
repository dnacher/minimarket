package com.minimarket.minimarketapp.persistence.repository;

import com.minimarket.minimarketapp.persistence.model.TransactionLine;
import org.springframework.data.repository.CrudRepository;

public interface TransactionLineRepository extends CrudRepository<TransactionLine, Integer> {
}
