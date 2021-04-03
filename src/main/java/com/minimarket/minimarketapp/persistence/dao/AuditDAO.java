package com.minimarket.minimarketapp.persistence.dao;

import com.minimarket.minimarketapp.error.ErrorHandling;
import com.minimarket.minimarketapp.exceptions.MiniMarketException;
import com.minimarket.minimarketapp.persistence.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.minimarket.minimarketapp.persistence.model.Audit;
import com.minimarket.minimarketapp.persistence.repository.AuditRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuditDAO {

    @Autowired
    private AuditRepository repository;
    private final String AUDIT = "La audit";

    public List<Audit> getAudit(){
        List<Audit> audits = new ArrayList<>();
        this.repository.findAll().forEach(audit -> audits.add(audit));
        return audits;
    }

    public Audit getAuditById(Integer id) throws MiniMarketException {
        return this.repository.findById(id).orElseThrow(() ->
             new MiniMarketException(ErrorHandling.valueNotFound(AUDIT,id)
        ));
    }

    public Audit saveAudit(Audit audit) throws MiniMarketException {
        return this.repository.save(audit);
    }

    public Audit saveErrorAudit() throws MiniMarketException {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setStatus(Status.ERROR);
        return this.repository.save(audit);
    }

    public Audit saveAudit(Integer id, Status status) throws MiniMarketException {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setStatus(status);
        audit.setTransactionId(id);
        return this.repository.save(audit);
    }

    public void deleteAudit(Audit audit){
        this.repository.delete(audit);
    }

}
