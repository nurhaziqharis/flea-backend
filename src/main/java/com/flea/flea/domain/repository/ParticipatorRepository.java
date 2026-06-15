package com.flea.flea.domain.repository;

import com.flea.flea.domain.entity.Participator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipatorRepository extends JpaRepository<Participator, UUID> {
}
