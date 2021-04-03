package com.minimarket.minimarketapp.domain.service.mappers;

import com.minimarket.minimarketapp.dto.TransactionLineDTO;
import com.minimarket.minimarketapp.persistence.model.TransactionLine;
import com.minimarket.minimarketapp.persistence.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionLineMapper implements AbstractMapper<TransactionLine, TransactionLineDTO>{

    @Autowired
    private TransactionRepository repository;

    @Override
    public TransactionLine mapToEntity(TransactionLineDTO dto) {
        TransactionLine transactionLine = new TransactionLine();
        transactionLine.setId(dto.getId());
        transactionLine.setSubtotal(dto.getSubtotal());
        transactionLine.setAmount(dto.getAmount());
        transactionLine.setProduct(dto.getProduct());
        transactionLine.setTransaction(repository.findById(dto.getTransactionId()).orElse(null));
        return transactionLine;
    }

    public List<TransactionLine> mapToEntityList(List<TransactionLineDTO> dtos) {
        List<TransactionLine> transactionLinesList = new ArrayList<>();
        for(TransactionLineDTO transactionLineDTO: dtos){
            transactionLinesList.add(mapToEntity(transactionLineDTO));
        }
        return transactionLinesList;
    }

    @Override
    public TransactionLineDTO mapToDTO(TransactionLine entity) {
        TransactionLineDTO transactionLineDTO = new TransactionLineDTO();
        transactionLineDTO.setId(entity.getId());
        transactionLineDTO.setSubtotal(entity.getSubtotal());
        transactionLineDTO.setAmount(entity.getAmount());
        transactionLineDTO.setProduct(entity.getProduct());
        transactionLineDTO.setTransactionId(entity.getTransaction().getId());
        return transactionLineDTO;
    }

    public List<TransactionLineDTO> mapToDTOList(List<TransactionLine> entities) {
        List<TransactionLineDTO> transactionLineDTOList = new ArrayList<>();
        for(TransactionLine transactionLine: entities){
            transactionLineDTOList.add(mapToDTO(transactionLine));
        }
        return transactionLineDTOList;
    }

}
