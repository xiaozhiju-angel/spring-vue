package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Adoptpet;
import com.pet.petspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdoptPetRepository extends JpaRepository<Adoptpet,Integer>, JpaSpecificationExecutor<Adoptpet> {
}
