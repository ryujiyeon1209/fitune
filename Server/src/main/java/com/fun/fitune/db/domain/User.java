package com.fun.fitune.db.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

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

    @Column(unique = true, name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "body_fat_percentage" , nullable = true)
    private Integer bodyFatPercentage;

    @Column(name = "Resting_BPM", nullable = true)
    private Integer RestingBPM;

    @Column(name = "active_BPM" , nullable = true)
    private Integer activeBPM;

}
