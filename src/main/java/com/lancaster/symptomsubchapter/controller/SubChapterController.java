package com.lancaster.symptomsubchapter.controller;

import com.lancaster.symptomsubchapter.model.SubChapter;
import com.lancaster.symptomsubchapter.service.SubChapterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subchapters")
public class SubChapterController {

    @Autowired
    private SubChapterService service;

    @GetMapping("")
    public List<SubChapter> fetchSubChapters() {
        return service.fetchSubChapters();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createSubChapter(@Valid @RequestBody SubChapter subChapter, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        try {
            int id = service.saveSubChapter(subChapter);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (RuntimeException e) {
            String errorMessage = "Error creating subchapter: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @RequestMapping(value = "subchapter/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SubChapter> findSubChapterWithName(@PathVariable("name") String name) {
        return new ResponseEntity<>(service.fetchSubChapterWithName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SubChapter> updateSubChapter(@RequestBody SubChapter subChapter, @PathVariable("id") int id) {
        SubChapter c = service.updateSubChapter(subChapter, id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "chapter/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SubChapter>> findSubChaptersForChapter(@PathVariable("name") String name) {
        return new ResponseEntity<>(service.fetchSubChaptersWithChapterName(name), HttpStatus.OK);
    }

}
