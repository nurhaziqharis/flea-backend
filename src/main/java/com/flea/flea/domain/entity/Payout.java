package com.flea.flea.domain.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payout extends BaseEntity{

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String poolTitle;

    @Column()
    private Long sequence = 0L;

    @Column()
    private LocalDateTime payoutDate;

    @ManyToOne
    @JoinColumn(
            name = "wallet_id"
    )
    private Wallet wallet;

    /// TODO: Association with Pool

}
