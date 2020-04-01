package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Pickup;
import com.pet.petspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PickupRepository extends JpaRepository<Pickup,Integer>, JpaSpecificationExecutor<Pickup> {
}
