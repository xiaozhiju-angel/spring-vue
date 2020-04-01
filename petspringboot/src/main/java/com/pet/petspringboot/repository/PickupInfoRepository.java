package com.pet.petspringboot.repository;

import com.pet.petspringboot.model.entity.Pickupinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PickupInfoRepository  extends JpaRepository<Pickupinfo,Integer>, JpaSpecificationExecutor<Pickupinfo> {
}
