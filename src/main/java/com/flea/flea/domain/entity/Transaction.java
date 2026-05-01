package com.flea.flea.domain.entity;

import com.flea.flea.enumaration.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction extends BaseEntity{

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false)
    private String toUsername;

    @Column(nullable = false)
    private String fromUsername;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column
    private String apiReferenceId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(
            name = "user_money_request_id"
    )
    private UserMoneyRequest userMoneyRequest;

    @ManyToOne
    @JoinColumn(
            name = "wallet_id"
    )
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(
            name = "payout_id"
    )
    private Payout payout;

    /// TODO: Association with PoolWallet

}
