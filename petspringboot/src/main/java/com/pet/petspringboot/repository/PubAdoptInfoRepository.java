package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Pubadoptinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PubAdoptInfoRepository extends JpaRepository<Pubadoptinfo,Integer>, JpaSpecificationExecutor<Pubadoptinfo> {
}
