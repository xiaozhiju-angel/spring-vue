package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Tipinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TipinfoRepository extends JpaRepository<Tipinfo,Integer>, JpaSpecificationExecutor<Tipinfo> {
}
