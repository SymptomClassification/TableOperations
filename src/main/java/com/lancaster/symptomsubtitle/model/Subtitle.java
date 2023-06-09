package com.lancaster.symptomsubtitle.model;

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
public class Subtitle {
    @Id
    @GeneratedValue
    int id;
    int chapterId;
    String name;
}
