package com.minimarket.minimarketapp.persistence.dao;

import com.minimarket.minimarketapp.domain.service.AuditService;
import com.minimarket.minimarketapp.error.ErrorHandling;
import com.minimarket.minimarketapp.exceptions.MiniMarketException;
import com.minimarket.minimarketapp.persistence.model.*;
import com.minimarket.minimarketapp.persistence.wrappers.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.minimarket.minimarketapp.persistence.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TransactionDAO {

    private final String TRANSACTION = "La transaccion";
    private final TransactionRepository repository;
    private final StockDAO stockDAO;
    private final AuditService auditService;

    @Autowired
    public TransactionDAO(TransactionRepository repository,
                          StockDAO stockDAO,
                          AuditService auditService){
        this.repository= repository;
        this.stockDAO = stockDAO;
        this.auditService = auditService;
    }

    public List<Transaction> getTransaction(){
        List<Transaction> transactions = new ArrayList<>();
        this.repository.findAll().forEach(transaction -> transactions.add(transaction));
        return transactions;
    }

    public Transaction getTransactionById(Integer id) throws MiniMarketException {
        return this.repository.findById(id).orElseThrow(() ->
                new MiniMarketException(ErrorHandling.valueNotFound(TRANSACTION,id),HttpStatus.NOT_FOUND));
    }

    public Transaction saveTransaction(Transaction transaction) throws MiniMarketException {
        List<Stock> stocks = new ArrayList<>();
        for(TransactionLine transactionLine: transaction.getLines()){
            Stock stock = stockDAO.getStockByProductId(transactionLine.getProduct().getId());
            updateStock(stock, transactionLine.getAmount());
            if(stock.getAmount()>=0){
                stocks.add(stock);
            }else{
                auditService.saveErrorAudit();
                throw new MiniMarketException(ErrorHandling.noStockMessage(stock.getProduct()),HttpStatus.BAD_REQUEST);
            }
            transactionLine.setTransaction(transaction);
            transactionLine.setSubtotal(transactionLine.getAmount() * transactionLine.getProduct().getUnitPrice());
        }
        transaction = this.repository.save(transaction);
        stockDAO.saveStocks(stocks);
        auditService.saveAudit(transaction.getId(),Status.CREATED);
        return transaction;
    }

    private void updateStock(Stock stock, Integer amount){
        stock.setAmount(stock.getAmount()-amount);
    }

    public List<Transaction> saveTransactions(List<Transaction> transactiones) throws MiniMarketException {
        List<Transaction> finalList= new ArrayList<>();
        this.repository.saveAll(transactiones).forEach(transaction -> {
            finalList.add(transaction);
        });
        return finalList;
    }

    public void deleteTransaction(Transaction transaction){
        this.repository.delete(transaction);
        auditService.saveAudit(transaction.getId(),Status.DELETED);
    }

    public Transaction updateTransaction(Transaction transaction) throws MiniMarketException {
        if(transaction.getId()!=null){
            transaction = this.repository.save(transaction);
            auditService.saveAudit(transaction.getId(),Status.UPDATED);
            return transaction;
        }else{
            String msg = String.format(ErrorHandling.valueUpdateError(TRANSACTION));
            auditService.saveErrorAudit();
            throw new MiniMarketException(msg,HttpStatus.BAD_REQUEST);
        }
    }

    public double totalByProductIdBetweenDates(Date fromDate, Date toDate, Integer productId){
        return repository.totalByProductIdBetweenDates(fromDate,toDate, productId);
    }

    public List<ProductWrapper> totalBetweenDatesGroupByProducts(Date fromDate, Date toDate){
        return repository.totalBetweenDatesGroupByProducts(fromDate, toDate);
    }
}
