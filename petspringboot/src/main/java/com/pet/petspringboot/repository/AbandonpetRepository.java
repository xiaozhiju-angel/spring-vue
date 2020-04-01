package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Abandonpet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AbandonpetRepository extends JpaRepository<Abandonpet,Integer>, JpaSpecificationExecutor<Abandonpet> {
}
