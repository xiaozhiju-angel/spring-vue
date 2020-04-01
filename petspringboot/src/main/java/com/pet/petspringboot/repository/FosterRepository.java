package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Fosterpet;
import com.pet.petspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FosterRepository extends JpaRepository<Fosterpet,Integer>, JpaSpecificationExecutor<Fosterpet> {
}
