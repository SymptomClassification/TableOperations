package com.lancaster.symptomsubtitle.service;

import com.lancaster.symptomsubtitle.model.Subtitle;
import com.lancaster.symptomsubtitle.repository.SubtitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtitleServiceImpl implements SubtitleService {

    @Autowired
    private SubtitleRepository repo;

    @Override
    public void saveSubtitle(Subtitle subtitle) {
        repo.saveSubtitle(subtitle);
    }

    @Override
    public Subtitle fetchSubtitleWithName(String name) {
        return repo.fetchSubtitleWithName(name).get();
    }

    @Override
    public Subtitle updateSubtitle(Subtitle subtitle, int id) {
        return repo.updateSubtitle(subtitle, id).get();
    }

    @Override
    public List<Subtitle> fetchSubtitles() {
        return repo.fetchSubtitles();
    }
}
