package com.example.smittetryk01.repository;

import com.example.smittetryk01.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, String> {

    Region findByName(String name);

}
