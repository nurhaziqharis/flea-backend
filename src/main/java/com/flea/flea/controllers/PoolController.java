package com.flea.flea.controllers;

import com.flea.flea.dto.response.PoolResponseOnly;
import com.flea.flea.service.PoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pools")
public class PoolController {

    final PoolService poolService;

    /*** GET all pools, optionally filtered by title ***/
    // GET http://localhost:8080/api/v1/pools
    // GET http://localhost:8080/api/v1/pools?title={title}
    @GetMapping
    public ResponseEntity<List<PoolResponseOnly>> getPools(
            @RequestParam(required = false) String title
    ){
        if (title != null) {
            return ResponseEntity.ok(poolService.getAllPoolsByTitle(title));
        }
        return ResponseEntity.ok(poolService.getAllPools());
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
}
