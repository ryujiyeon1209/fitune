package com.fun.fitune.db.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "prefer_exercise")
public class PreferExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefer_exercise_seq")
    private int preferExerciseSeq;

    @ManyToOne
    @JoinColumn(name = "exercise_seq")
    private ExerciseList exerciseList;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;
}
