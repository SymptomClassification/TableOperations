package com.lancaster.symptomsubchapter.service;

import com.lancaster.symptomsubchapter.model.SubChapter;
import com.lancaster.symptomsubchapter.repository.SubChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class SubChapterServiceImpl implements SubChapterService {

    @Autowired
    private SubChapterRepository repo;

    @Override
    public int saveSubChapter(SubChapter subChapter) {
        try {
            return repo.saveSubchapter(subChapter);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException("Subchapter name and chapterId must be unique and cannot be null", e);
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error saving subchapter: " + e.getMessage(), e);
        }
    }

    @Override
    public SubChapter fetchSubChapterWithName(String name) {
        return repo.fetchSubChapterWithName(name).get();
    }

    @Override
    public SubChapter updateSubChapter(SubChapter subChapter, int id) {
        return repo.updateSubChapter(subChapter, id).get();
    }

    @Override
    public List<SubChapter> fetchSubChapters() {
        return repo.fetchSubChapters();
    }

    @Override
    public List<SubChapter> fetchSubChaptersWithChapterName(String name) {
        return repo.selectSubChaptersForChapters(name);
    }
}
