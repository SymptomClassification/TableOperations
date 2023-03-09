package com.lancaster.trainingdata.service;

import com.lancaster.trainingdata.model.TrainingData;
import com.lancaster.trainingdata.repository.TrainingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrainingDataServiceImpl implements TrainingDataService {

    @Autowired
    private TrainingDataRepository repo;

    @Override
    public List<TrainingData> fetchTrainingData() {
        return repo.fetchTrainingData();
    }

    @Override
    public List<Map<String, String>> fetchTrainingDataDefinitions() {
        return repo.getTrainingDataDescriptions();
    }

    @Override
    public TrainingData updateTrainingData(TrainingData trainingData, int id) {
        return repo.updateTrainingData(trainingData, id).get();
    }
}
