package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.model.Chapter;

import java.util.List;

public interface ChapterService {

    List<Chapter> fetchChapters();

    int saveChapter(Chapter chapter);

    Chapter fetchChapterWithName(String name);

    Chapter updateChapter(Chapter chapter, int id);
}
