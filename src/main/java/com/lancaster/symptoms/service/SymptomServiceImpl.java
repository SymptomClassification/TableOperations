package com.lancaster.symptoms.service;


import com.lancaster.symptoms.model.Symptom;
import com.lancaster.symptoms.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository repo;

    @Override
    public int saveSymptom(Symptom symptom) {
        return repo.saveSymptom(symptom);
    }

    @Override
    public List<Symptom> fetchSymptoms() {
        return repo.fetchSymptoms();
    }

    @Override
    public Symptom getSymptomWithId(int id) {
        return repo.fetchSymptomWithId(id).get();
    }

    @Override
    public Symptom updateSymptom(Symptom symptom, int id) {
        return repo.updateSymptom(symptom, id).get();
    }


}
