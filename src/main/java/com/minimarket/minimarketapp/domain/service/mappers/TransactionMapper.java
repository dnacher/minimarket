package com.minimarket.minimarketapp.domain.service.mappers;

import com.minimarket.minimarketapp.dto.TransactionDTO;
import com.minimarket.minimarketapp.persistence.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper implements AbstractMapper<Transaction, TransactionDTO>{

    @Autowired
    private TransactionLineMapper transactionLineMapper;

    @Override
    public Transaction mapToEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setDateTransaction(dto.getDateTransaction());
        transaction.setLines(transactionLineMapper.mapToEntityList(dto.getLines()));
        return transaction;
    }

    public List<Transaction> mapToEntity(List<TransactionDTO> dtoList) {
        List<Transaction> transactions = new ArrayList<>();
        for(TransactionDTO transactionDTO: dtoList){
            transactions.add(mapToEntity(transactionDTO));
        }
        return transactions;
    }

    @Override
    public TransactionDTO mapToDTO(Transaction entity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(entity.getId());
        transactionDTO.setDateTransaction(entity.getDateTransaction());
        transactionDTO.setLines(transactionLineMapper.mapToDTOList(entity.getLines()));
        return transactionDTO;
    }

    public List<TransactionDTO> mapToDTOList(List<Transaction> entities) {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for(Transaction transaction: entities){
            transactionDTOList.add(mapToDTO(transaction));
        }
        return transactionDTOList;
    }
}
