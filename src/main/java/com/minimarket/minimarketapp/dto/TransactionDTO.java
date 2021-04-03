package com.minimarket.minimarketapp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDTO implements Serializable {

    private Integer id;
    private Date dateTransaction;
    private List<TransactionLineDTO> lines = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public List<TransactionLineDTO> getLines() {
        return lines;
    }

    public void setLines(List<TransactionLineDTO> lines) {
        this.lines = lines;
    }
}
