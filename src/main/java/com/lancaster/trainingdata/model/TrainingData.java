package com.lancaster.trainingdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingData {
    @Id
    @GeneratedValue
    int id;
    String symptom;
    int label;
}
