package com.lancaster.spacychapters.service;

import com.lancaster.spacychapters.repository.SpacyChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpacyChapterServiceImpl implements SpacyChapterService {

    @Autowired
    private SpacyChapterRepository repo;

    @Override
    public List<Map<String, String>> getSpacyChapters() {
        return repo.getChapters();
    }

    @Override
    public List<Map<String, String>> getSpacySubChapters() {
        return repo.getSubChapters();
    }
}
