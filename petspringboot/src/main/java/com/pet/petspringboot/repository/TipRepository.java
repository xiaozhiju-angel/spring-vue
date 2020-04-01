package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TipRepository extends JpaRepository<Tip,Integer>, JpaSpecificationExecutor<Tip> {
}
