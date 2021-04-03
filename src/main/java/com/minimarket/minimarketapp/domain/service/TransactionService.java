package com.minimarket.minimarketapp.domain.service;

import com.minimarket.minimarketapp.domain.service.mappers.TransactionMapper;
import com.minimarket.minimarketapp.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimarket.minimarketapp.persistence.dao.TransactionDAO;
import com.minimarket.minimarketapp.persistence.model.Transaction;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionDAO transactionDAO;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO,
                              TransactionMapper transactionMapper) {
        this.transactionDAO = transactionDAO;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionDTO> getTransaction() {
        return transactionMapper.mapToDTOList(transactionDAO.getTransaction());
    }

    public TransactionDTO getTransactionById(Integer id) {
        return transactionMapper.mapToDTO(transactionDAO.getTransactionById(id));
    }

    public TransactionDTO saveTransaction(Transaction transaction) {
        return transactionMapper.mapToDTO(transactionDAO.saveTransaction(transaction));
    }

    public TransactionDTO updateTransaction(Transaction transaction) {
        return transactionMapper.mapToDTO(transactionDAO.updateTransaction(transaction));
    }

    public void deleteTransaction(Transaction transaction) {
        transactionDAO.deleteTransaction(transaction);
    }

    public double totalByProductIdBetweenDates(Date fromDate, Date toDate, Integer productId){
        return transactionDAO.totalByProductIdBetweenDates(fromDate,toDate,productId);
    }

}
