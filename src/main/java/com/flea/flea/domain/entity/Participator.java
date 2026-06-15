package com.flea.flea.domain.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "participators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participator extends BaseEntity{

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String poolTitle;

    @Column(nullable = false)
    private Boolean isAgree = false;

    @Column(nullable = false)
    private Integer turn = 0;

    @ManyToOne
    @JoinColumn(
            name = "pool_id",
            nullable = false
    )
    private Pool participatorPool;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User participatorUser;

}
