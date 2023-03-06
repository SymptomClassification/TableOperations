package com.lancaster.symptomchapter.controller;


import com.lancaster.symptomchapter.model.Chapter;
import com.lancaster.symptomchapter.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    @Autowired
    private ChapterService service;

    @GetMapping()
    public List<Chapter> fetchChapters() {
        return service.fetchChapters();
    }

    // TODO move error handling to service layer
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createChapter(@Validated @RequestBody Chapter chapter) {
        try {
            int id = service.saveChapter(chapter);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (RuntimeException e) {
            String errorMessage = "Error creating chapter: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @RequestMapping(value = "chapter/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Chapter> findChapterWithName(@PathVariable("name") String name) {
        return new ResponseEntity<>(service.fetchChapterWithName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Chapter> updateChapter(@RequestBody Chapter chapter, @PathVariable("id") int id) {
        Chapter c = service.updateChapter(chapter, id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


}
