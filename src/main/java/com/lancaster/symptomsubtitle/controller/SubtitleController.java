package com.lancaster.symptomsubtitle.controller;


import com.lancaster.symptomsubtitle.model.Subtitle;
import com.lancaster.symptomsubtitle.service.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subtitles")
public class SubtitleController {

    @Autowired
    private SubtitleService service;

    @GetMapping("fetchSubtitles")
    public List<Subtitle> fetchSubtitles() {
        return service.fetchSubtitles();
    }

    @RequestMapping(value = "createSubtitle", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createChapter(@Validated @RequestBody Subtitle subtitle) {
        service.saveSubtitle(subtitle);
    }

    @RequestMapping(value = "findSubtitleWithName/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public Subtitle findSubtitleWithName(@PathVariable("name") String name) {
        return service.fetchSubtitleWithName(name);
    }

    @RequestMapping(value = "updateSubtitle/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Subtitle> updateSubtitle(@RequestBody Subtitle subtitle, @PathVariable("id") int id) {
        Subtitle s = service.updateSubtitle(subtitle, id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
