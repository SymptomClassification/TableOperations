package com.lancaster.symptomsecondsubchapter.model;

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
public class SecondSub {

    @Id
    @GeneratedValue
    private int id;
    private int subId;
    private String major;
    private String name;

}
