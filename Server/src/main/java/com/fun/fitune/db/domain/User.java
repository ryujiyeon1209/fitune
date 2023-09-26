package com.fun.fitune.db.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Getter
@Setter
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    @Column(name = "user_seq", nullable = false)
    private int userSeq;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "body_fat_percentage")
    private int bodyFatPercentage;

    @Column(name = "Resting_BPM")
    private int RestingBPM;

    @Column(name = "active_BPM")
    private int activeBPM;

    @Column(name = "Resting_BPM")
    private int RestingBPM;

    @Column(name = "active_BPM")
    private int activeBPM;
}
