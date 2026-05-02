package com.flea.flea.domain.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ranks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rank extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private String username;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

}
