package com.minimarket.minimarketapp.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.minimarket.minimarketapp.persistence.model.Audit;

@Repository
public interface AuditRepository extends CrudRepository<Audit, Integer> {
}
