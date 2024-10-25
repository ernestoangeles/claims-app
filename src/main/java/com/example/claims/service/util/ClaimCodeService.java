package com.example.claims.service.util;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ClaimCodeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String generateCode() {
        Long nextVal = ((Number) entityManager
                .createNativeQuery("SELECT nextval('claims_schema.claim_sequence')")
                .getSingleResult()).longValue();

        return String.format("REC%06d", nextVal);
    }
}