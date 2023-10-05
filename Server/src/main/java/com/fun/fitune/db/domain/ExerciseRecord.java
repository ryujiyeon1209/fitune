package com.fun.fitune.db.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

public class ExerciseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "exercise_record_seq")
    private int exerciseRecordSeq;

    @Column(name="exercise_start")
    private Timestamp exerciseStart;

    @Column(name="exercise_end")
    private Timestamp exerciseEnd;


}
