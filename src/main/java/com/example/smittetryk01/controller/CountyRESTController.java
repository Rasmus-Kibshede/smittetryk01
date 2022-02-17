package com.example.smittetryk01.controller;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountyRESTController {

    @Autowired
    private CountyRepository countyRepository;

    @GetMapping("/counties")
    public List<County> getAll() {
        return countyRepository.findAll();
    }

    @GetMapping("/")
    public String hej() {
        return "Hello world";
    }

    @GetMapping("/county/{id}")
    public County findCounty(@PathVariable String id) {
        Optional<County> obj = countyRepository.findById(id);

        if (obj.isPresent()) {
            return obj.get();
        }

        return null;
    }

    @PostMapping("/county")
    @ResponseStatus(HttpStatus.CREATED)
    public County postCounty(@RequestBody County county) {
        System.out.println(county);

        return countyRepository.save(county);
    }


    @PutMapping("/county/{id}")
    public ResponseEntity<County> putCounty(@PathVariable String id, @RequestBody County county) {
        Optional<County> optCounty = countyRepository.findById(id);

        if (optCounty.isPresent()) {
            countyRepository.save(county);

            return new ResponseEntity<>(county, HttpStatus.OK);
        } else {
            County notFound = new County();
            notFound.setName("Kunne ikke finde id=" + id);

            return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCounty(@PathVariable String id) {
        countyRepository.deleteById(id);

        Optional<County> o = countyRepository.findById(id);

        if (!o.isPresent()) {
            return new ResponseEntity<>("county med id = " + id + " er blevet slettet", HttpStatus.OK);
        }
        return new ResponseEntity<>("Kunne ikke finde id = " + id, HttpStatus.NOT_FOUND);
    }


}
