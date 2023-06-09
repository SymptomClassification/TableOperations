package com.lancaster.symptomsubchapter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubChapter {

    @Id
    @GeneratedValue
    private int id;
    @NotNull()
    private String name;
    @NotNull()
    private int chapterId;

}
