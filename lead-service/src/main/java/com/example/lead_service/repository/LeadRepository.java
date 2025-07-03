package com.example.lead_service.repository;
import com.example.lead_service.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findById(long id);

    @Query(value = "SELECT l FROM Lead l WHERE l.agentId =:agentId")
    List<Lead> findByAgentId(String agentId);
}

