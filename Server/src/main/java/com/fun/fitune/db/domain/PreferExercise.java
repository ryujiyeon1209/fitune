package com.fun.fitune.db.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
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

    @JsonIgnore
    @ManyToOne(targetEntity = ExerciseList.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_list_seq", referencedColumnName = "exercise_list_seq")
    private ExerciseList exerciseList;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;
}
