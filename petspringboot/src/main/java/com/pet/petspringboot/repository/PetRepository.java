package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PetRepository extends JpaRepository<Pet,Integer>, JpaSpecificationExecutor<Pet> {
}
