package com.flea.flea.implementation;

import com.flea.flea.domain.entity.Pool;
import com.flea.flea.domain.repository.PoolRepository;
import com.flea.flea.dto.response.PoolResponseOnly;
import com.flea.flea.mapper.PoolMapper;
import com.flea.flea.service.PoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PoolImplementation implements PoolService {

    private final PoolRepository poolRepository;
    private final PoolMapper poolMapper;

    @Override
    public List<PoolResponseOnly> getAllPools() {
        return poolRepository.findAll()
                .stream()
                .map(poolMapper::toPoolResponseOnly)
                .toList();
    }

    @Override
    public PoolResponseOnly getPoolById(UUID id) {
        Pool pool = poolRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Pool not found"));
        return poolMapper.toPoolResponseOnly(pool);
    }

    @Override
    public List<PoolResponseOnly> getAllPoolsByTitle(String title) {
        List<Pool> poolList = poolRepository.findAllByTitleContainingIgnoreCase(title);
        if(poolList == null){
            throw new IllegalStateException("Pool not found");
        }
        return poolList
                .stream()
                .map(poolMapper::toPoolResponseOnly)
                .toList();
    }
}
