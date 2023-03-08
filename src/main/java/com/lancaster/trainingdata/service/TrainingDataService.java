package com.lancaster.trainingdata.service;

import com.lancaster.trainingdata.model.TrainingData;

import java.util.List;
import java.util.Map;

public interface TrainingDataService {

    List<TrainingData> fetchTrainingData();

    List<Map<String, String>> fetchTrainingDataDefinitions();
}
