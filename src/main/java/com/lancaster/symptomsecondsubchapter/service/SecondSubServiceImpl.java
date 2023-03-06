package com.lancaster.symptomsecondsubchapter.service;

import com.lancaster.symptomsecondsubchapter.model.SecondSub;
import com.lancaster.symptomsecondsubchapter.repository.SecondSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondSubServiceImpl implements SecondSubService {

    @Autowired
    private SecondSubRepository repo;

    @Override
    public void saveSecondSub(SecondSub secondSub) {
        repo.saveSecondSub(secondSub);
    }

    @Override
    public SecondSub fetchSecondSubWithName(String name) {
        return repo.fetchSecondSubWithName(name).get();
    }

    @Override
    public SecondSub updateSecondSub(SecondSub secondSub, int id) {
        return repo.updateSecondSub(secondSub, id).get();
    }

    @Override
    public void deleteSecondSub(int id) {
        repo.deleteSecondSub(id);
    }

    @Override
    public List<SecondSub> fetchSecondSubs() {
        return repo.fetchSecondSubs();
    }
}
