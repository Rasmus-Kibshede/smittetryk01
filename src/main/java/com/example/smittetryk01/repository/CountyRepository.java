package com.example.smittetryk01.repository;

import com.example.smittetryk01.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, String> {

    Optional<County> findByName(String name);
}
