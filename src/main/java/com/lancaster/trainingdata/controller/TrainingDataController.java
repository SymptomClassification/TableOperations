package com.lancaster.trainingdata.controller;

import com.lancaster.trainingdata.model.TrainingData;
import com.lancaster.trainingdata.service.TrainingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/definitions")
    public List<Map<String, String>> fetchTrainingDataDefinitions() {
        return service.fetchTrainingDataDefinitions();
    }

}
