package com.lancaster.symptomsecondsubchapter.service;

import com.lancaster.symptomsecondsubchapter.model.SecondSub;

import java.util.List;

public interface SecondSubService {
    void saveSecondSub(SecondSub secondSub);

    SecondSub fetchSecondSubWithName(String name);

    SecondSub updateSecondSub(SecondSub secondSub, int id);

    void deleteSecondSub(int id);

    List<SecondSub> fetchSecondSubs();
}
