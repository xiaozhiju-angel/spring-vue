package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Lose;
import com.pet.petspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoseRepository extends JpaRepository<Lose,Integer>, JpaSpecificationExecutor<Lose> {
}
