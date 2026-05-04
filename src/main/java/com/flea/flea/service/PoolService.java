package com.flea.flea.service;

import com.flea.flea.dto.response.PoolResponseOnly;
import java.util.List;
import java.util.UUID;

public interface PoolService {

    List<PoolResponseOnly> getAllPools();

    PoolResponseOnly getPoolById(UUID id);

    List<PoolResponseOnly> getAllPoolsByTitle(String title);

}
