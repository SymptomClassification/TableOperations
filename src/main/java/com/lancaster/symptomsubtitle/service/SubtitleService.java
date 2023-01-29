package com.lancaster.symptomsubtitle.service;

import com.lancaster.symptomsubtitle.model.Subtitle;

import java.util.List;

public interface SubtitleService {

    void saveSubtitle(Subtitle subtitle);

    Subtitle fetchSubtitleWithName(String name);

    Subtitle updateSubtitle(Subtitle subtitle, int id);

    List<Subtitle> fetchSubtitles();


}
