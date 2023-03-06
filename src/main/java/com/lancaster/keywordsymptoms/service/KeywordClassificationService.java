package com.lancaster.keywordsymptoms.service;

import com.lancaster.keywordsymptoms.model.KeywordClassifiedSymptom;

import java.util.List;
import java.util.Map;

public interface KeywordClassificationService {

    List<KeywordClassifiedSymptom> fetchClassifiedSymptoms();

    KeywordClassifiedSymptom saveClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom);

    KeywordClassifiedSymptom fetchClassifiedSymptomWitSymptomId(int symptomId);

    KeywordClassifiedSymptom updateClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom, int symptomId);


}
