package com.minimarket.minimarketapp.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.minimarket.minimarketapp.persistence.model.Transaction;

import java.util.Date;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query(value = "SELECT SUM(subtotal) " +
            "FROM transaction_table " +
            "JOIN transaction_line on transaction_line.transaction_id=transaction_table.transaction_id " +
            "WHERE transaction_line.product_id=:productId " +
            "AND (transaction_table.date BETWEEN :fromDate AND :toDate);", nativeQuery = true)
    double totalByProductIdBetweenDates(Date fromDate, Date toDate, Integer productId);

}
