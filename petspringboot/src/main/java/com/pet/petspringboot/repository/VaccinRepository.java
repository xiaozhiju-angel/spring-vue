package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.User;
import com.pet.petspringboot.model.entity.Vaccin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VaccinRepository extends JpaRepository<Vaccin,Integer>, JpaSpecificationExecutor<Vaccin> {
}
