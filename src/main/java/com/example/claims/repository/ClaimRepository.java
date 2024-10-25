package com.example.claims.repository;

import com.example.claims.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, UUID> {

    @Query("SELECT c FROM Claim c WHERE c.id = :id")
    Optional<Claim> findByIdWithStatusHistory(UUID id);

    @Query("SELECT c FROM Claim c ORDER BY c.creationDate DESC")
    List<Claim> findAllSorted();
}
