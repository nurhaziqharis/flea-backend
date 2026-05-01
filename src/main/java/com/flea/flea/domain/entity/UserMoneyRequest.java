package com.flea.flea.domain.entity;

import com.flea.flea.enumaration.MoneyRequestStatus;
import com.flea.flea.enumaration.RequestType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "user_money_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMoneyRequest extends BaseEntity{

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MoneyRequestStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @ManyToOne
    @JoinColumn(
            name = "wallet_id",
            nullable = false
    )
    private Wallet wallet;

}
