package com.minimarket.minimarketapp.domain.service;

import com.minimarket.minimarketapp.exceptions.MiniMarketException;
import com.minimarket.minimarketapp.persistence.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimarket.minimarketapp.persistence.dao.AuditDAO;
import com.minimarket.minimarketapp.persistence.model.Audit;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AuditService {

    @Autowired
    private AuditDAO auditDAO;

    public List<Audit> getAudit(){
        return auditDAO.getAudit();
    }

    public Audit getAuditById(Integer id){
        try {
            return auditDAO.getAuditById(id);
        } catch (MiniMarketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Audit saveAudit(Audit audit){
        try {
            return auditDAO.saveAudit(audit);
        } catch (MiniMarketException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Audit saveErrorAudit(){
        return auditDAO.saveErrorAudit();
    }

    public Audit saveAudit(Integer id, Status status){
        return auditDAO.saveAudit(id, status);
    }



    public void deleteAudit(Audit audit){
        auditDAO.deleteAudit(audit);
    }
    
}
