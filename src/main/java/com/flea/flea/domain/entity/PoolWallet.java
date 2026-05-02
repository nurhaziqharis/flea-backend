package com.flea.flea.domain.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pool_wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PoolWallet extends BaseEntity{

    @Column(nullable = false)
    private String poolTitle;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal walletAmount = BigDecimal.ZERO;

}
