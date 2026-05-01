package com.flea.flea.domain.entity;
import com.flea.flea.enumaration.PoolStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pools")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pool extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal poolAmount;

    @Column(nullable = false)
    private Long minParticipants = 1L;

    @Column(nullable = false)
    private Long maxParticipants = 12L;

    @Column(nullable = false)
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private PoolStatus status;

    @Column(nullable = false)
    private Long dayPayments = 1L;

    /// TODO: Associate with Agreement

}
