package com.lancaster.symptomsubtitle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public record Subtitle(@Id @GeneratedValue int id, int chapterId, String name) {
    public Subtitle() {
        this(0, 0, "");
    }
}
