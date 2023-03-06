package com.lancaster.symptomsecondsubchapter.controller;

import com.lancaster.symptomsecondsubchapter.model.SecondSub;
import com.lancaster.symptomsecondsubchapter.service.SecondSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secondsubchapters")
public class SecondSubController {

    @Autowired
    private SecondSubService service;

    @GetMapping("")
    public List<SecondSub> fetchChapters() {
        return service.fetchSecondSubs();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createSecondSub(@Validated @RequestBody SecondSub secondSub) {
        service.saveSecondSub(secondSub);
    }

    @RequestMapping(value = "secondsubchapter/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SecondSub> findSecondSubWithName(@PathVariable("name") String name) {
        return new ResponseEntity<>(service.fetchSecondSubWithName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SecondSub> updateSecondSub(@RequestBody SecondSub secondSub, @PathVariable("id") int id) {
        SecondSub c = service.updateSecondSub(secondSub, id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


}
