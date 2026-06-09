package com.flea.flea.controllers;

import com.flea.flea.dto.request.NewPoolRequest;
import com.flea.flea.dto.response.PoolResponseOnly;
import com.flea.flea.service.PoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pools")
public class PoolController {

    final PoolService poolService;

    /*** GET all pools ***/
    // GET /api/v1/pools?start=0&off=20
    // GET /api/v1/pools?start=0&off=20&filter=title:LIKE:savings&filter=status:EQ:ACTIVE
    @GetMapping
    public ResponseEntity<Page<PoolResponseOnly>> getPools(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(defaultValue = "20") int off,
            @RequestParam(required = false) List<String> filter
    ) {
        return ResponseEntity.ok(poolService.getPools(start, off, filter));
    }

    /*** GET pool by id ***/
    // GET http://localhost:8080/api/v1/pools/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PoolResponseOnly> getPool(
            @PathVariable UUID id
    ){
        PoolResponseOnly pool = poolService.getPoolById(id);
        return ResponseEntity.ok(pool);
    }

    @PostMapping
    public ResponseEntity<PoolResponseOnly> createNewPool(
            @RequestBody NewPoolRequest newPoolRequest
    ){
        return ResponseEntity.ok(poolService.createNewPool(newPoolRequest));
    }
}
