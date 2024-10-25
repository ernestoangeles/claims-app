package com.example.claims.repository;

import com.example.claims.model.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, UUID> {
}