package com.lancaster.symptoms.service;


import com.lancaster.symptoms.model.Symptom;

import java.util.List;

public interface SymptomService {

    int saveSymptom(Symptom symptom);

    List<Symptom> fetchSymptoms();

    Symptom getSymptomWithId(int id);

    Symptom updateSymptom(Symptom symptom, int id);

}
