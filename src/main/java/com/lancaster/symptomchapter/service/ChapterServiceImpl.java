package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.model.Chapter;
import com.lancaster.symptomchapter.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository repo;

    @Override
    public List<Chapter> fetchChapters() {
        return repo.fetchChapters();
    }

    @Override
    public int saveChapter(Chapter chapter) {
        return repo.saveChapter(chapter);
    }

    @Override
    public Chapter fetchChapterWithName(String name) {
        return repo.fetchChapterWithName(name).get();
    }

    @Override
    public Chapter updateChapter(Chapter chapter, int id) {
        return repo.updateChapter(chapter, id).get();
    }

}
