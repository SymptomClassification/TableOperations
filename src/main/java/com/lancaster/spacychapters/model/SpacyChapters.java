package com.lancaster.spacychapters.model;

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
public class SpacyChapters {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int chapterId;
}
