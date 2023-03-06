package com.lancaster.symptoms.controller;


import com.lancaster.symptoms.model.Symptom;
import com.lancaster.symptoms.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
public class SymptomController {

    @Autowired
    private SymptomService service;

    @RequestMapping()
    public List<Symptom> fetchSymptoms() {
        return service.fetchSymptoms();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> createSymptom(@Validated @RequestBody Symptom symptom) {
        return new ResponseEntity<>(service.saveSymptom(symptom), HttpStatus.OK);
    }

    @RequestMapping(value = "symptom/{id}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public Symptom findSymptomWithId(@PathVariable("id") int id) {
        return service.getSymptomWithId(id);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Symptom updateSymptom(@RequestBody Symptom symptom, @PathVariable("id") int id) {
        return service.updateSymptom(symptom, id);
    }

}
