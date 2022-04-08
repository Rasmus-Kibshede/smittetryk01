package com.example.smittetryk01.controller;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CountyRESTControllerPaging {

    @Autowired
    private CountyRepository countyRepository;


    @GetMapping("countyPageSort")
    public ResponseEntity<Map<String, Object>> getCountyPageAndSort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "countyCode,desc") String[] sort) {


        List<Sort.Order> orders = new ArrayList<>();
        Sort.Direction direction = getSortDirection(sort[1]);

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(direction, _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(direction, sort[0]));
        }

        Pageable request = PageRequest.of(page, size, Sort.by(orders));

        Page<County> countyPage = countyRepository.findAll(request);

        List<County> countyList = countyPage.getContent();

        if (countyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> map = new HashMap();
        map.put("county", countyList);
        map.put("currentPage", countyPage.getNumber());
        map.put("totalPages", countyPage.getTotalPages());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @GetMapping("countySort")
    public ResponseEntity<List<County>> getPageOfCounties(@RequestParam(defaultValue = "countyCode,desc") String[] sort) {

        List<Sort.Order> orders = new ArrayList<>();
        Sort.Direction direction = getSortDirection(sort[1]);


        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(direction, _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(direction, sort[0]));
        }

        List counties = countyRepository.findAll(Sort.by(orders));

        if (counties.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(counties, HttpStatus.OK);


    }






    /*@GetMapping("countyPage")
    public ResponseEntity<List<County>> getPageOfCounties() {
        int page = 4;
        int size = 5;

        Pageable request = PageRequest.of(page, size);

        Page<County> countyPage = countyRepository.findAll(request);

        List<County> countyList = countyPage.getContent();

        if (countyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(countyList, HttpStatus.OK);
        }
    }*/


    @GetMapping("countyPage")
    public ResponseEntity<Map<String, Object>> getPageOfCountiesParm(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable request = PageRequest.of(page, size);

        Page<County> countyPage = countyRepository.findAll(request);

        List<County> countyList = countyPage.getContent();

        if (countyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> map = new HashMap();
        map.put("county", countyList);
        map.put("currentPage", countyPage.getNumber());
        map.put("totalPages", countyPage.getTotalPages());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}