package com.fun.fitune.db.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "cell")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cell_seq")
    private int cellSeq;

    @Column(name = "cell_name")
    private String cellName;

    @Column(name = "cell_exp")
    private long cellExp;

    @Column(name = "cell_latest_exp")
    private int cellLatestExp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;
}
