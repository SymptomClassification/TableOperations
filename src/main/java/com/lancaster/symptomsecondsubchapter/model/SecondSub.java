package com.lancaster.symptomsecondsubchapter.model;

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
public class SecondSub {

    @Id
    @GeneratedValue
    private int id;
    private int subId;
    private String major;
    private String name;

}
