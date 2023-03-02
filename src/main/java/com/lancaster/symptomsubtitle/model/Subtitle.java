package com.lancaster.symptomsubtitle.model;

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
public class Subtitle {
    @Id
    @GeneratedValue
    int id;
    int chapterId;
    String name;
}
