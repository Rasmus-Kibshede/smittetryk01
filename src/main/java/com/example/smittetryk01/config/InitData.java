package com.example.smittetryk01.config;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.model.Region;
import com.example.smittetryk01.repository.CountyRepository;
import com.example.smittetryk01.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public void run(String... args) throws Exception {

        Region region = new Region();
        region.setRegionCode("1084");
        region.setName("Region Hovedstaden");
        region.setHref("https://api.dataforsyningen.dk/regioner/1084");
        regionRepository.save(region);

        County county = new County();
        county.setName("København");
        county.setCountyCode("0101");
        county.setHref("http://localhost:8080/county/0101");
        county.setRegion(region);
        countyRepository.save(county);

        County valby = new County();
        valby.setName("Valby");
        valby.setCountyCode("1000");
        valby.setHref("http://localhost:8080/county/1000");
        valby.setRegion(region);
        countyRepository.save(valby);
    }
}
