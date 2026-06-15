package com.flea.flea.implementation;

import com.flea.flea.domain.entity.Participator;
import com.flea.flea.domain.repository.ParticipatorRepository;
import com.flea.flea.dto.response.ParticipatorResponseBase;
import com.flea.flea.mapper.ParticipatorMapper;
import com.flea.flea.service.ParticipatorService;
import com.flea.flea.specification.ParticipatorSpecification;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ParticipatorImplementation implements ParticipatorService {

    private final ParticipatorRepository participatorRepository;
    private final ParticipatorMapper participatorMapper;
    private final CommonAction commonAction;

    @Override
    public Page<ParticipatorResponseBase> getAllParticipator(Integer start, Integer off, List<String> filters) {
        Specification<Participator> spec = ParticipatorSpecification.fromFilters(
                filters == null ? List.of() : filters);
        return participatorRepository.findAll(spec, PageRequest.of(start, off))
                .map(participatorMapper::toParticipatorResponseBase);
    }

    @Override
    public ParticipatorResponseBase getParticipator(String id) {
        UUID uuid = commonAction.convertStringToUUID(id);
        Participator participator = participatorRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Participator is not exist"));
        return participatorMapper.toParticipatorResponseBase(participator);
    }
}
