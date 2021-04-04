package com.minimarket.minimarketapp.persistence.repository;

import com.minimarket.minimarketapp.persistence.wrappers.ProductWrapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.minimarket.minimarketapp.persistence.model.Transaction;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query(value = "SELECT SUM(subtotal) " +
            "FROM transaction_table " +
            "JOIN transaction_line on transaction_line.transaction_id=transaction_table.transaction_id " +
            "WHERE transaction_line.product_id=:productId " +
            "AND (transaction_table.date BETWEEN :fromDate AND :toDate);", nativeQuery = true)
    double totalByProductIdBetweenDates(Date fromDate, Date toDate, Integer productId);

    @Query(value = "SELECT product.code as productId,product.description as name, SUM(subtotal) as total " +
            "FROM transaction_table " +
            "JOIN transaction_line on transaction_table.transaction_id=transaction_line.transaction_id " +
            "JOIN product on transaction_line.product_id=product.code " +
            "WHERE (transaction_table.date BETWEEN '2021-04-01' AND '2021-04-24') " +
            "GROUP BY transaction_line.product_id " +
            "ORDER BY 1;", nativeQuery = true)
    List<ProductWrapper> totalBetweenDatesGroupByProducts(Date fromDate, Date toDate);
}
