package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Adoptinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdoptInfoRepository extends JpaRepository<Adoptinfo,Integer>, JpaSpecificationExecutor<Adoptinfo> {
}
