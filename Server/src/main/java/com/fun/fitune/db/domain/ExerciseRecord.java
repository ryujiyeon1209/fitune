package com.fun.fitune.db.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "exercise_record")
public class ExerciseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "exercise_record_seq")
    private int exerciseRecordSeq;

    @Column(name="exercise_start")
    private LocalDateTime exerciseStart;

    @Nullable
    @Column(name="exercise_end")
    private LocalDateTime exerciseEnd;

    @Column(name = "exercise_reco")
    private boolean exerciseReco;

    @Column(name = "exercise_avg_bpm")
    private int exerciseAvgBpm;

    @Column(name = "exercise_max_bpm")
    private int exerciseMaxBpm;

    @Column(name = "exercise_distance")
    private int exerciseDistance;

    @Column(name = "exercise_review")
    private int exerciseReview;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;

    @JsonIgnore
    @ManyToOne(targetEntity = ExerciseList.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_list_seq", referencedColumnName = "exercise_list_seq")
    private ExerciseList exerciseList;
}
