package com.lancaster.symptomsubchapter.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubChapter {

    @Id @GeneratedValue
    private int id;
    @NotNull()
    private String name;
    @NotNull()
    private int chapterId;

}
