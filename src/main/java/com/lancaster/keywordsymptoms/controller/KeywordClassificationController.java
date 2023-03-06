package com.lancaster.keywordsymptoms.controller;


import com.lancaster.keywordsymptoms.model.KeywordClassifiedSymptom;
import com.lancaster.keywordsymptoms.service.KeywordClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classifiedSymptoms")
public class KeywordClassificationController {

    @Autowired
    private KeywordClassificationService service;

    @RequestMapping()
    public List<KeywordClassifiedSymptom> fetchClassifiedSymptoms() {
        return service.fetchClassifiedSymptoms();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<KeywordClassifiedSymptom> createClassifiedSymptom(@Validated @RequestBody KeywordClassifiedSymptom symptom) {
        return new ResponseEntity<>(service.saveClassifiedSymptom(symptom), HttpStatus.OK);
    }

    @RequestMapping(value = "classifiedSymptom/{id}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<KeywordClassifiedSymptom> findClassifiedSymptomWithName(@PathVariable("id") int symptomId) {
        return new ResponseEntity<>(service.fetchClassifiedSymptomWitSymptomId(symptomId), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<KeywordClassifiedSymptom> updateClassifiedSymptom(@RequestBody KeywordClassifiedSymptom symptom, @PathVariable("id") int symptomId) {
        KeywordClassifiedSymptom c = service.updateClassifiedSymptom(symptom, symptomId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


}
