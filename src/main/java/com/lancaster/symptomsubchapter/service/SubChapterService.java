package com.lancaster.symptomsubchapter.service;

import com.lancaster.symptomsubchapter.model.SubChapter;

import java.util.List;

public interface SubChapterService {
    int saveSubChapter(SubChapter subChapter);

    SubChapter fetchSubChapterWithName(String name);

    SubChapter updateSubChapter(SubChapter subChapter, int id);

    List<SubChapter> fetchSubChapters();

    List<SubChapter> fetchSubChaptersWithChapterName(String name);
}
