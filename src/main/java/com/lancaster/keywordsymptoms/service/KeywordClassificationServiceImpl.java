package com.lancaster.keywordsymptoms.service;


import com.lancaster.keywordsymptoms.model.KeywordClassifiedSymptom;
import com.lancaster.keywordsymptoms.repository.KeywordClassifiedSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordClassificationServiceImpl implements KeywordClassificationService {

    @Autowired
    private KeywordClassifiedSymptomRepository repo;


    @Override
    public List<KeywordClassifiedSymptom> fetchClassifiedSymptoms() {
        return repo.fetchClassifiedSymptoms();
    }

    @Override
    public KeywordClassifiedSymptom saveClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom) {
        return repo.saveClassifiedSymptom(keywordClassifiedSymptom);
    }

    @Override
    public KeywordClassifiedSymptom fetchClassifiedSymptomWitSymptomId(int symptomId) {
        return repo.fetchClassifiedSymptomWithSymptomId(symptomId).get();
    }

    @Override
    public KeywordClassifiedSymptom updateClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom, int symptomId) {
        return repo.updateClassifiedSymptom(keywordClassifiedSymptom, symptomId).get();
    }


}
