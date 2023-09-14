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
@ToString
@Table(name = "exercise_list")
public class ExerciseList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_list_seq")
    private Byte exerciseListSeq;

    @Column(name = "exercise_name")
    private String exerciseName;
}
