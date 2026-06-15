package com.flea.flea.implementation;

import com.flea.flea.domain.entity.Participator;
import com.flea.flea.domain.entity.Pool;
import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.repository.ParticipatorRepository;
import com.flea.flea.domain.repository.PoolRepository;
import com.flea.flea.domain.repository.UserRepository;
import com.flea.flea.dto.request.NewParticipatorRequest;
import com.flea.flea.dto.response.ParticipatorResponseBase;
import com.flea.flea.mapper.ParticipatorMapper;
import com.flea.flea.service.ParticipatorService;
import com.flea.flea.specification.ParticipatorSpecification;
import com.flea.flea.validator.ParticipatorValidator;
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
    private final ParticipatorValidator participatorValidator;
    private final UserRepository userRepository;
    private final PoolRepository poolRepository;

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

    @Override
    @Transactional
    public ParticipatorResponseBase createNewParticipator(NewParticipatorRequest newParticipatorRequest) {
        participatorValidator.newParticipatorValidator(newParticipatorRequest);
        UUID userUUID = commonAction.convertStringToUUID(newParticipatorRequest.getUserId());
        UUID poolUUID = commonAction.convertStringToUUID(newParticipatorRequest.getPoolId());
        User user = userRepository.findById(userUUID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Pool pool = poolRepository.findById(poolUUID)
                .orElseThrow(() -> new RuntimeException("Pool not found"));
        Participator newParticipator = Participator
                .builder()
                .participatorPool(pool)
                .participatorUser(user)
                .isAgree(newParticipatorRequest.getIsAgree())
                .username(newParticipatorRequest.getUsername())
                .poolTitle(newParticipatorRequest.getPoolTitle())
                .turn(newParticipatorRequest.getTurn())
                .build();

        participatorRepository.save(newParticipator);
        return participatorMapper.toParticipatorResponseBase(newParticipator);
    }
}
