package com.minimarket.minimarketapp.persistence.repository;

import com.minimarket.minimarketapp.persistence.model.TransactionLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionLineRepository extends CrudRepository<TransactionLine, Integer> {

    @Query("SELECT sum(tl.subtotal) from TransactionLine tl where tl=:transactionLine")
    double sumProductSalesAmount(TransactionLine transactionLine);

}
