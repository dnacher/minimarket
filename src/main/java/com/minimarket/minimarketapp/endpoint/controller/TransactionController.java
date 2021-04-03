package com.minimarket.minimarketapp.endpoint.controller;

import com.minimarket.minimarketapp.domain.service.TransactionService;
import com.minimarket.minimarketapp.domain.service.mappers.TransactionMapper;
import com.minimarket.minimarketapp.dto.TransactionDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.minimarket.minimarketapp.persistence.model.Transaction;
import com.minimarket.minimarketapp.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper){
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping(value = "/")
    public TransactionDTO saveTransaction(@RequestBody Transaction transaction){
        return this.transactionService.saveTransaction(transaction);
    }

    @PostMapping(value = "/mul")
    public List<TransactionDTO> saveTransactions(@RequestBody List<Transaction> transactions){
        List<TransactionDTO> finalList = new ArrayList<>();
        transactions.forEach(transaction -> {
            finalList.add(this.transactionService.saveTransaction(transaction));
        });
        return finalList;
    }

    @PutMapping(value = "/{id}")
    public TransactionDTO updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction){
        String msg = String.format("The Transaction Id %s is different from the Url Id",transaction.getId());
        Utils.validateUrlIdEqualsBodyId(id,transaction.getId(),msg);
        return this.transactionService.updateTransaction(transaction);
    }

    @PutMapping(value = "/mul")
    public List<TransactionDTO> updateTransaction(@RequestBody List<Transaction> transactions){
        List<TransactionDTO> finalList = new ArrayList<>();
        transactions.forEach(transaction -> {
            finalList.add(this.transactionService.updateTransaction(transaction));
        });
        return finalList;
    }

    @GetMapping(value = "/")
    public List<TransactionDTO> getTransaction(){
        return this.transactionService.getTransaction();
    }

    @GetMapping(value = "/{id}")
    public TransactionDTO getTransactionById(@PathVariable Integer id){
        return this.transactionService.getTransactionById(id);
    }

    @GetMapping(value = "/product/")
    public double getTransaction(@RequestParam Integer id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate){
        return this.transactionService.totalByProductIdBetweenDates(fromDate, toDate, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTransaction(@PathVariable Integer id, Transaction transaction){
        String msg = String.format("The Transaction Id %s is different from the Url Id",transaction.getId());
        Utils.validateUrlIdEqualsBodyId(id,transaction.getId(),msg);
        this.transactionService.deleteTransaction(transaction);
    }
}
