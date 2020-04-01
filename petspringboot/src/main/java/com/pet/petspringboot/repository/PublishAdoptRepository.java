package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Publishadopt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PublishAdoptRepository extends JpaRepository<Publishadopt,Integer>, JpaSpecificationExecutor<Publishadopt> {
}
