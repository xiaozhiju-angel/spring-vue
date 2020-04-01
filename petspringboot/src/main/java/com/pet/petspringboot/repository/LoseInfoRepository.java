package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Loseinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoseInfoRepository extends JpaRepository<Loseinfo,Integer>, JpaSpecificationExecutor<Loseinfo> {
}
