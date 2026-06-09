package com.flea.flea.implementation;

import com.flea.flea.domain.entity.Participator;
import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.repository.ParticipatorRepository;
import com.flea.flea.domain.repository.PoolRepository;
import com.flea.flea.dto.request.NewPoolRequest;
import com.flea.flea.dto.response.PoolResponseOnly;
import com.flea.flea.mapper.PoolMapper;
import com.flea.flea.service.PoolService;
import com.flea.flea.specification.PoolSpecification;
import com.flea.flea.validator.PoolValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import com.flea.flea.domain.entity.Pool;
import com.flea.flea.enumeration.PoolStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PoolImplementation implements PoolService {

    private final PoolRepository poolRepository;
    private final ParticipatorRepository participatorRepository;
    private final PoolMapper poolMapper;
    private final PoolValidator poolValidator;
    private final CommonAction commonAction;

    @Override
    public Page<PoolResponseOnly> getPools(int start, int off, List<String> filters) {
        Specification<Pool> spec = PoolSpecification.fromFilters(
                filters == null ? List.of() : filters);
        return poolRepository.findAll(spec, PageRequest.of(start, off))
                .map(poolMapper::toPoolResponseOnly);
    }

    @Override
    public PoolResponseOnly getPoolById(UUID id) {
        Pool pool = poolRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Pool not found"));
        return poolMapper.toPoolResponseOnly(pool);
    }

    @Override
    @Transactional
    public PoolResponseOnly createNewPool(NewPoolRequest newPoolRequest) {
        poolValidator.newPoolValidator(newPoolRequest);
        User poolAdminUser = commonAction.getCurrentUser();
        Pool pool = poolRepository.save(Pool.builder()
                .title(newPoolRequest.getTitle())
                .poolAmount(newPoolRequest.getPoolAmount())
                .minParticipants(newPoolRequest.getMinParticipants())
                .maxParticipants(newPoolRequest.getMaxParticipants())
                .description(newPoolRequest.getDescription())
                .dayPayments(newPoolRequest.getDayPayments())
                .status(PoolStatus.RECRUITING)
                .poolAdmin(poolAdminUser)
                .build());

        poolRepository.save(pool);
        return poolMapper.toPoolResponseWithAdmin(pool);
    }


}
