package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Acceptpet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcceptRepository extends JpaRepository<Acceptpet,Integer>, JpaSpecificationExecutor<Acceptpet> {
}
