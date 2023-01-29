package com.lancaster.symptomsubtitle.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subtitle {
    @Id @GeneratedValue
    private int id;
    private int chapterId;
    private String name;
}
