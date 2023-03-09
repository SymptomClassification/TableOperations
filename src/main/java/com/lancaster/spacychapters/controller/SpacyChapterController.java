package com.lancaster.spacychapters.controller;

import com.lancaster.spacychapters.service.SpacyChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spacychapters")
public class SpacyChapterController {

    @Autowired
    private SpacyChapterService service;


    @RequestMapping("")
    public List<Map<String, String>> getSpacyChapters() {
        return service.getSpacyChapters();
    }

    @RequestMapping("/subchapters")
    public List<Map<String, String>> getSpacySubChapters() {
        return service.getSpacySubChapters();
    }

    @RequestMapping("/subchapters/{id}")
    public List<Map<String, String>> getSubchaptersOfChapter(@PathVariable("id") int id) {
        return service.getSubchaptersOfChapter(id);
    }
}
