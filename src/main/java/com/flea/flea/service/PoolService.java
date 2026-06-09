package com.flea.flea.service;

import com.flea.flea.dto.request.NewPoolRequest;
import com.flea.flea.dto.response.PoolResponseOnly;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.UUID;

public interface PoolService {

    Page<PoolResponseOnly> getPools(int start, int off, List<String> filters);

    PoolResponseOnly getPoolById(UUID id);

    PoolResponseOnly createNewPool(NewPoolRequest newPoolRequest);

}
