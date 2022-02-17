package com.example.smittetryk01.config;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private CountyRepository countyRepository;


    @Override
    public void run(String... args) throws Exception {

        County copenhagen = new County();
        copenhagen.setName("Region Hovedstaden");
        copenhagen.setCountyCode("0101");
        copenhagen.setHref("http://localhost:8080/county/0101");
        countyRepository.save(copenhagen);

        County valby = new County();
        valby.setName("Valby");
        valby.setCountyCode("1000");
        valby.setHref("http://localhost:8080/county/1000");
        countyRepository.save(valby);
    }
}
