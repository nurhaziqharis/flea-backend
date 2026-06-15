package com.flea.flea.domain.repository;

import com.flea.flea.domain.entity.Participator;
import com.flea.flea.domain.entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipatorRepository extends JpaRepository<Participator, UUID>, JpaSpecificationExecutor<Participator> {

    Participator findByTurnAndParticipatorPool_Id(Integer turn, UUID id);

}
