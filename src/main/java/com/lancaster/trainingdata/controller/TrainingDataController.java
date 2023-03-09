package com.lancaster.trainingdata.controller;

import com.lancaster.trainingdata.model.TrainingData;
import com.lancaster.trainingdata.service.TrainingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trainings")
public class TrainingDataController {

    @Autowired
    private TrainingDataService service;

    @RequestMapping("")
    public List<TrainingData> fetchTrainingData() {
        return service.fetchTrainingData();
    }

    @RequestMapping("definitions")
    public List<Map<String, String>> fetchTrainingDataDefinitions() {
        return service.fetchTrainingDataDefinitions();
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TrainingData> updateTrainingData(@RequestBody TrainingData trainingData, @PathVariable("id") int id) {
        TrainingData updatedTrainingData = service.updateTrainingData(trainingData, id);
        return new ResponseEntity<>(updatedTrainingData, HttpStatus.OK);
    }
}
