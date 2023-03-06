package com.lancaster.keywordsymptoms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.lang.Nullable;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordClassifiedSymptom {
    @Id
    @GeneratedValue
    private int id;
    private int symptomId;
    private int chapterId;
    @Nullable
    private int subchapterId;
    @Nullable
    private int secondsubId;

}
