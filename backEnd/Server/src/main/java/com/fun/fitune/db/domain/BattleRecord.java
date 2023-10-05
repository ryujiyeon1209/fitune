package com.fun.fitune.db.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@Entity
@Getter
@Table(name = "battle_record")
@Builder
public class BattleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battle_record_seq")
    private int battleRecordSeq;

    @Column(name = "battle_date")
    private LocalDateTime battleDate;

    @Column(name = "winner_seq")
    private int winnerSeq;

    @Column(name = "battle_user_seq")
    private int battleUserSeq;

    @Column(name = "battle_other_seq")
    private int battleOtherSeq;
}
