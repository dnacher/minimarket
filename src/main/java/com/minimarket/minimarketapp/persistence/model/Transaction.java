package com.minimarket.minimarketapp.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction_table")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "date")
    private Date dateTransaction;

    @OneToMany(
            mappedBy = "transaction",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TransactionLine> lines = new ArrayList<>();

    public Transaction(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date date) {
        this.dateTransaction = date;
    }

    public List<TransactionLine> getLines() {
        return lines;
    }

    public void setLines(List<TransactionLine> lines) {
        this.lines = lines;
    }
}
