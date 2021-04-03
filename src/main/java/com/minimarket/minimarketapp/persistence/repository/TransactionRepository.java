package com.minimarket.minimarketapp.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.minimarket.minimarketapp.persistence.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
